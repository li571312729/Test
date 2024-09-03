package com.net.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * 我们自定义一个handler需要去继承netty规定好的某个HandlerAdapter
 *
 * @author lxq
 * @date 2021年04月25日 14:54
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {
    // 用来保存所有的客户端连接
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM");

    /**
     * 当Channel中有新的事件消息会自动调用
     *
     * @param ctx 上下文对象，包含pipline，socketChannel
     * @param msg 客户端发送的数据
     * @author lxq
     * @date 2021/4/25 15:02
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 这个和NIO提供的ByteBuffer不同，但是性质类似，不过这个ByteBuf性能更好
        ByteBuf data = (ByteBuf) msg;
        int i = data.readableBytes();
        byte[] a = new byte[i];
        data.readBytes(a);

        System.out.println("接收到客户端信息：" + new String(a, CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress().toString());

    }

    /**
     * 上面方法读取完毕后触发的事件，可以用于消息返回
     *
     * @param ctx
     * @author lxq
     * @date 2021/4/25 15:09
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端收到", CharsetUtil.UTF_8));
    }

    // 当有新的客户端连接服务器之后，会自动调用这个方法
    // 这里不是连接事件，连接事件是在BossGroup中进行的，这里是连接之后
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 将新的通道加入到clients
        clients.add(ctx.channel());
    }

    /**
     * 连接异常
     *
     * @return null
     * @author lxq
     * @date 2021/4/10 16:42
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    /**
     * 空闲事件
     *
     * @param ctx
     * @param evt
     * @author lxq
     * @date 2021/4/10 16:41
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String socketString = ctx.channel().remoteAddress().toString();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("Client: " + socketString + " READER_IDLE 读空闲");
                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("Client: " + socketString + " WRITER_IDLE 写空闲");
                ctx.disconnect();
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.info("Client: " + socketString + " ALL_IDLE 总空闲");
                ctx.disconnect();
            }
        }
    }

    /**
     * 客户端断开连接
     *
     * @param ctx
     * @author lxq
     * @date 2021/4/10 16:40
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("关闭通道");
        ctx.close();
    }


}

