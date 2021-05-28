package com.net.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lxq
 * @date 2021年04月25日 15:33
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪时触发该方法
     * @author lxq
     * @date 2021/4/25 15:34
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client:{}, is OK", ctx.channel().localAddress().toString());
        for (int i = 0; i < 10; i++){
            ctx.write(Unpooled.copiedBuffer("hello 中国", CharsetUtil.UTF_8));
        }
        // 上面是写入TCP缓冲区，只有刷新才能发送数据包出去
        ctx.flush();
    }

    /**
     * 当Channel中有新的事件消息会自动调用
     * @author lxq
     * @date 2021/4/25 15:36
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 这个和NIO提供的ByteBuffer不同，但是性质类似，不过这个ByteBuf性能更好
        ByteBuf data = (ByteBuf) msg;
        System.out.println("接收到服务端信息：" + data.toString(CharsetUtil.UTF_8));
    }


    /**
     * 连接异常
     * @author lxq
     * @date 2021/4/10 16:42
     * @return null
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

