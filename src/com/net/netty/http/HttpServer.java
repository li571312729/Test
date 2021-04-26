package com.net.netty.http;

import com.net.netty.simple.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 实现http服务
 * @author lxq
 * @date 2021年04月26日 11:26
 */
@Slf4j
public class HttpServer {

    private static final int PORT = 7777;

    public static void main(String[] args) {

        // 创建BossGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 创建workerGroup 采用默认线程数
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务器启动对象，可以设置并配置参数
            ServerBootstrap bootstrap = new ServerBootstrap()
                    // 设置两个线程组
                    .group(bossGroup,workerGroup)
                    // 指定使用一个NIO传输Channel类型
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpInit());
            // 启动服务器并绑定端口，开始同步接收进来的连接
            // future表示一个异步操作的结果，通过future就可以关注你所执行的异步操作
            ChannelFuture future = bootstrap.bind(PORT).sync();
            log.info("Netty Ser   ver start listen at " + PORT);
            // 对关闭通道进行同步监听，指的是通信双方关闭通道
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.info("Netty Server stop: " + PORT + ", {}", e);
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }



}

