package com.net.bio.socket;


import com.net.bio.socket.interfaces.SocketClientResponseInterface;
import com.net.bio.socket.thread.SocketClientThread;
import com.net.bio.talk.util.Utils;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by gavinandre on 18-3-13.
 */
@Slf4j
public class SocketClient {

    private static final String TAG = SocketClient.class.getSimpleName();

    private SocketClientThread socketClientThread;

    public SocketClient(SocketClientResponseInterface socketClientResponseInterface) {
        socketClientThread = new SocketClientThread("socketClientThread", socketClientResponseInterface);
        socketClientThread.start();
    }

    public <T> void sendData(T data) {
        //convert to string or serialize object
        if (!(data instanceof String)) {
            log.error(TAG, "sendData: todo serialize object");
            return;
        }
        String s = (String) data;
        if (Utils.isNullOrEmpty(s)) {
            log.error(TAG, "sendData: 消息不能为空");
            return;
        }
        if (socketClientThread != null) {
            socketClientThread.sendMsg(s);
        }
    }

    public void stopSocket() {
        //一定要在子线程内执行关闭socket等IO操作
        new Thread(() -> {
            socketClientThread.setReConnect(false);
            socketClientThread.stopThread();
            log.info(TAG, "stopSocket: ");
        }).start();
    }


    public static void main(String[] args) {

        SocketClient socketClient = new SocketClient(new SocketClientResponseInterface(){
            @Override
            public void onSocketConnect() {
                System.out.println("连接成功");
            }

            @Override
            public void onSocketReceive(String socketResult, int code) {
                System.out.println("客户端收到消息：" + socketResult);
            }

            @Override
            public void onSocketDisable(String msg, int code) {
                System.out.println("客户端关闭回调" + msg);
            }
        });

    }
}
