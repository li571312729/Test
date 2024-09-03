package com.net.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lxq
 * @date 2021年04月28日 13:56
 */

public class Server {

    private static final Logger log = LoggerFactory.getLogger(Server.class);


    private static final int PORT = 8888;

    public static void main(String[] args) {
        // 创建BossGroup 采用默认的EventLoop子线程数 数量为cpu核数 * 2
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
                    // 增加日志handler ，设置日志级别
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    // 线程队列中等待连接的个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 设置workGroup对应的EventLoopGroup事件处理, 这里因为是处理ServerSocketChannel
                    // 因此ChannelInitializer中泛型为对应的SocketChannel，这个可以看作单reactor单线程中对应的事件发生时调用的方法，
                    // 所以需要SocketChannel客户端通道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 因为EventLoopGroup下对应的是pipline,这里给pipline设置事件处理器，pipline中可以获取客户端连接通道
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            // 通过pipeline获取对应的channel，这里pipeline和channel是互相关联的
                            ChannelPipeline p = ch.pipeline();
                            // 这个是心跳监测事件读写器，超过指定的时间后触发的处理器
                            p.addLast(new IdleStateHandler(0, 0, 0));

                            // websocket和http协议数据格式类似，添加http编解码器
                            p.addLast(new HttpServerCodec());

                            // 以块方式发送数据，添加ChunkWrite处理器
                            p.addLast(new ChunkedWriteHandler());

                            // HttpObjectAggregator是Http消息聚合器，Aggregator这个单次就是“聚合，
                            // 聚集”的意思。http消息在传输的过程中可能是一片片的消息片端，
                            // 所以当服务器接收到的是一片片的时候，就需要HttpObjectAggregator来把它们聚合起来。
                            p.addLast(new HttpObjectAggregator(512*1024)); // http 消息聚合器

                            // 添加websocket控制器
                            // websocket 数据是以帧形式传递，
                            // 识别浏览器的ws：，  websocket协议
                            // 升级http协议升级为 websocket使用的 ws：协议，保持长连接
                            p.addLast(new WebSocketServerProtocolHandler("/test"));

                            // 添加自定义处理器
                            p.addLast(new WebsocketServerHandler());
                        }
                    });
            // 启动服务器并绑定端口，开始同步接收进来的连接
            // future表示一个异步操作的结果，通过future就可以关注你所执行的异步操作
            ChannelFuture future = bootstrap.bind(PORT).sync();

            // 给futre注册监听器，监控我们关心的事件, 这里应该使用GenericFutureListener他的具体实现类ChannelFutureListener
            future.addListener(new GenericFutureListener(){
                @Override
                public void operationComplete(Future future) throws Exception {
                    if(future.isSuccess()){
                        log.info("服务器启动{}成功。",PORT);
                    }else {
                        log.info("服务器启动{}失败。",PORT);
                    }
                }
            });
            // 对关闭通道进行同步监听，指的是通信双方关闭通道
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.info("Netty Server stop: " + PORT + ", {}", e);
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

