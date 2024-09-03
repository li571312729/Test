package com.net.bio.socket.thread;

import com.net.bio.socket.interfaces.SocketClientResponseInterface;
import com.net.bio.socket.interfaces.SocketCloseInterface;
import com.net.bio.socket.utils.SocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;

/**
 * Created by gavinandre on 18-3-13.
 * 数据接收线程
 */
@Slf4j
public class SocketReceiveThread extends Thread {

    private static final String TAG = SocketReceiveThread.class.getSimpleName();

    private volatile String name;

    private volatile boolean isCancel = false;

    private BufferedReader bufferedReader;

    private SocketCloseInterface socketCloseInterface;

    private SocketClientResponseInterface socketClientResponseInterface;

    public SocketReceiveThread(String name, BufferedReader bufferedReader,
                               SocketClientResponseInterface socketClientResponseInterface,
                               SocketCloseInterface socketCloseInterface) {
        this.name = name;
        this.bufferedReader = bufferedReader;
        this.socketClientResponseInterface = socketClientResponseInterface;
        this.socketCloseInterface = socketCloseInterface;
    }

    @Override
    public void run() {
        final Thread currentThread = Thread.currentThread();
        final String oldName = currentThread.getName();
        currentThread.setName("Processing-" + name);
        try {
            while (!isCancel) {
                if (bufferedReader != null) {
                    String receiverData = SocketUtil.readFromStream(bufferedReader);
                    if (receiverData != null) {
                        successMessage(receiverData);
                    } else {
                        log.info(TAG, "run: receiverData==null");
                        break;
                    }
                }
            }
        } finally {
            //循环结束则退出输入流
            SocketUtil.closeBufferedReader(bufferedReader);
            currentThread.setName(oldName);
            log.info(TAG, "SocketReceiveThread finish");
        }
    }

    /**
     * 接收消息回调
     */
    private void successMessage(String data) {
        // Log.i(TAG, "onSocketReceive: " + data);
        if (socketClientResponseInterface != null) {
            socketClientResponseInterface.onSocketReceive(data, SocketUtil.SUCCESS);
        }
    }

    public void close() {
        isCancel = true;
        this.interrupt();
        if (bufferedReader != null) {
            if (socketCloseInterface != null) {
                socketCloseInterface.onSocketShutdownInput();
            }
            SocketUtil.closeBufferedReader(bufferedReader);
            bufferedReader = null;
        }
    }

}
