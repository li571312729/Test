package com.net.netty.httpfileServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * netty 基于http的文件服务
 * @author lxq
 * @date 2021年05月27日 15:56
 */
@Slf4j
public class HttpFileServer {

    private static Integer PORT = 6625;

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 添加http请求消息解码器
                            pipeline.addLast(new HttpRequestDecoder());
                            // 添加将多个消息转换为单一的FullHttpRequest 或者 FullHttpResponse 的消息聚合器
                            pipeline.addLast(new HttpObjectAggregator(65535));
                            // 添加Http相应消息编码器
                            pipeline.addLast(new HttpResponseEncoder());
                            // 支持异步发送大的码流，例如大的文件传输，但不占用过多的内存，防止发生java内存溢出错误
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpFileServerHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind(new InetSocketAddress(PORT)).sync();
            future.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess()){
                        log.info("监听端口{}成功。",PORT);
                    }else {
                        log.info("监听端口{}失败。",PORT);
                    }
                }
            });
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
    
    

}

