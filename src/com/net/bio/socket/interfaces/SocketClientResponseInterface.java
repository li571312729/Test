package com.net.bio.socket.interfaces;

/**
 * Created by gavinandre on 18-2-25.
 */
public interface SocketClientResponseInterface {

    /**
     * 客户端连接成功回调
     */
    void onSocketConnect();

    /**
     * 客户端收到服务端消息回调
     *
     * @param socketResult
     * @param code
     */
    void onSocketReceive(String socketResult, int code);

    /**
     * 客户端关闭回调
     *
     * @param msg
     * @param code
     */
    void onSocketDisable(String msg, int code);
}
