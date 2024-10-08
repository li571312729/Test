package com.net.bio.socket.thread;


import com.net.bio.socket.interfaces.SocketClientResponseInterface;
import com.net.bio.socket.interfaces.SocketCloseInterface;
import com.net.bio.socket.utils.SocketUtil;
import lombok.extern.slf4j.Slf4j;

import javax.net.SocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 写数据采用死循环，没有数据时wait，有新消息时notify
 * Created by gavinandre on 18-1-8.
 */
@Slf4j
public class SocketClientThread extends Thread implements SocketCloseInterface {

    private static final String TAG = SocketClientThread.class.getSimpleName();

    private volatile String name;

    private boolean isLongConnection = true;
    private boolean isReConnect = true;
    private int reconnectCount = 0;
    private SocketSendThread mSocketSendThread;
    private SocketReceiveThread mSocketReceiveThread;
    private SocketHeartBeatThread mSocketHeartBeatThread;
    private Socket mSocket;

    private boolean isSocketAvailable;

    private SocketClientResponseInterface socketClientResponseInterface;

    public SocketClientThread(String name, SocketClientResponseInterface socketClientResponseInterface) {
        this.name = name;
        this.socketClientResponseInterface = socketClientResponseInterface;
    }

    @Override
    public void run() {
        final Thread currentThread = Thread.currentThread();
        final String oldName = currentThread.getName();
        currentThread.setName("Processing-" + name);
        try {
            initSocket();
            log.info(TAG, "run: " + currentThread.getName() + " end");
        } finally {
            currentThread.setName(oldName);
        }
    }

    /**
     * 初始化socket客户端
     */
    private void initSocket() {
        try {
            mSocket = SocketFactory.getDefault().createSocket();
            SocketAddress socketAddress = new InetSocketAddress(SocketUtil.ADDRESS, SocketUtil.PORT);
            mSocket.connect(socketAddress, 10000);
//            mSocket.setKeepAlive(true);
            isSocketAvailable = true;

            //开启接收线程
            mSocketReceiveThread = new SocketReceiveThread("SocketReceiveThread",
                    new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "UTF-8")),
                    socketClientResponseInterface, this);
            mSocketReceiveThread.start();

            //开启发送线程
            PrintWriter printWriter = new PrintWriter(mSocket.getOutputStream(), true);
            log.info(TAG, "initSocket: " + printWriter);
            mSocketSendThread = new SocketSendThread("SocketSendThread", printWriter);
            mSocketSendThread.setCloseSendTask(false);
            mSocketSendThread.start();

            //开启心跳线程
            if (isLongConnection) {
                mSocketHeartBeatThread = new SocketHeartBeatThread("SocketHeartBeatThread",
                        printWriter, mSocket, this);
                mSocketHeartBeatThread.start();
            }

            if (socketClientResponseInterface != null) {
                socketClientResponseInterface.onSocketConnect();
            }
        } catch (ConnectException e) {
            failedMessage("服务器连接异常，请检查网络", SocketUtil.FAILED);
            log.warn(TAG, "initSocket: " + e.getMessage());
            stopThread();
        } catch (IOException e) {
            failedMessage("网络发生异常，请稍后重试", SocketUtil.FAILED);
            log.warn(TAG, "initSocket: " + e.getMessage());
            stopThread();
        }
    }

    /**
     * 发送消息
     */
    public void sendMsg(String data) {
        if (mSocketSendThread != null) {
            mSocketSendThread.sendMsg(data);
        }
    }

    /**
     * 关闭socket客户端
     */
    public synchronized void stopThread() {
        //关闭接收线程
        closeReceiveTask();
        //唤醒发送线程并关闭
        wakeSendTask();
        //关闭心跳线程
        closeHeartBeatTask();
        //关闭socket
        closeSocket();
        //清除数据
        clearData();
        failedMessage("断开连接", SocketUtil.FAILED);
        if (reconnectCount > 60) {
            log.error(TAG, "stopThread: reconnect over 5 minutes, stop reconnect");
            return;
        }
        if (isReConnect) {
            reconnectCount++;
            log.info(TAG, "reconnect: " + Thread.currentThread().getName());
            SocketUtil.toWait(this, 5 * 1000);
            initSocket();
            reconnectCount = 0;
            log.info(TAG, "stopThread: " + Thread.currentThread().getName());
        }
    }

    /**
     * 唤醒后关闭发送线程
     */
    private void wakeSendTask() {
        if (mSocketSendThread != null) {
            mSocketSendThread.wakeSendTask();
        }
    }

    /**
     * 关闭接收线程
     */
    private void closeReceiveTask() {
        if (mSocketReceiveThread != null) {
            mSocketReceiveThread.close();
            mSocketReceiveThread = null;
        }
    }

    /**
     * 关闭心跳线程
     */
    private void closeHeartBeatTask() {
        if (mSocketHeartBeatThread != null) {
            mSocketHeartBeatThread.close();
        }
    }

    /**
     * 关闭socket
     */
    private void closeSocket() {
        if (mSocket != null) {
            if (!mSocket.isClosed() && mSocket.isConnected()) {
                try {
                    mSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            isSocketAvailable = false;
            mSocket = null;
        }
    }

    /**
     * 清除数据
     */
    private void clearData() {
        if (mSocketSendThread != null) {
            mSocketSendThread.clearData();
        }
    }

    /**
     * 连接失败回调
     */
    private void failedMessage(String msg, int code) {
        if (socketClientResponseInterface != null) {
            socketClientResponseInterface.onSocketDisable(msg, code);
        }
    }

    /**
     * 判断本地socket连接状态
     */
    private boolean isConnected() {
        if (mSocket.isClosed() || !mSocket.isConnected()) {
            stopThread();
            return false;
        }
        return true;
    }

    @Override
    public void onSocketShutdownInput() {
        if (isSocketAvailable) {
            SocketUtil.inputStreamShutdown(mSocket);
        }
    }

    @Override
    public void onSocketDisconnection() {
        isSocketAvailable = false;
        new Thread(this::stopThread).start();
    }

    /**
     * 设置是否断线重连
     */
    public void setReConnect(boolean reConnect) {
        isReConnect = reConnect;
    }

}
