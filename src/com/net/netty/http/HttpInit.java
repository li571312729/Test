package com.net.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author lxq
 * @date 2021年04月26日 11:29
 */
public class HttpInit extends ChannelInitializer<SocketChannel> {

    // 因为EventLoopGroup下对应的是pipline,这里给pipline设置事件处理器，pipline中可以获取客户端连接通道
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        // 通过pipeline获取对应的channel，这里pipeline和channel是互相关联的
        ChannelPipeline p = ch.pipeline();
        // http 编码，解码器
        p.addLast("myCodec", new HttpServerCodec());
        // 添加自定义处理器
        p.addLast("myHttpHandler", new HttpHandler());
    }
}

