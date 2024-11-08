package com.net.netty.websocket;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @author lxq
 * @date 2021年04月28日 17:29
 */
@Slf4j
public class WebsocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用来保存所有的客户端连接
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM");

    /**
     * 当Channel中有新的事件消息会自动调用
      * @author lxq
      * @date 2021/4/29 13:59
      * @return null
      */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        log.info("服务器收到消息:{}", textWebSocketFrame.text());
        channelHandlerContext.writeAndFlush(new TextWebSocketFrame("服务器时间" + LocalDateTime.now() + "," + textWebSocketFrame.text()));
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

    }

    // 表示channel 客户端活动
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 推送新用户上线的消息
        clients.writeAndFlush(Unpooled.copiedBuffer(ctx.channel().remoteAddress() + "用户上线", CharsetUtil.UTF_8));

        // 将新的通道加入到clients
        clients.add(ctx.channel());

        log.info(ctx.channel().remoteAddress() + "用户上线");
    }

    /**
     * 客户端断开连接
     * 触发这个方法的时候 ChannelGroup clients 会自动断开当前channel
     * 而且当心跳监测事件发生进行ctx.disconnect()，产生异常关闭连接都会触发该方法。
     * @param ctx
     * @author lxq
     * @date 2021/4/10 16:40
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().remoteAddress() + "用户离线");
        ctx.close();
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
     * 心跳监测
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
}

