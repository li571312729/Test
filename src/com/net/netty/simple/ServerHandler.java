package com.net.netty.simple;

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
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * 我们自定义一个handler需要去继承netty规定好的某个HandlerAdapter
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
     * @author lxq
     * @date 2021/4/25 15:02
     * @param ctx 上下文对象，包含pipline，socketChannel
     * @param msg 客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  throws Exception {
        // 这个和NIO提供的ByteBuffer不同，但是性质类似，不过这个ByteBuf性能更好
        ByteBuf data = (ByteBuf) msg;
        int i = data.readableBytes();
        byte[] a = new byte[i];
        data.readBytes(a);
        UserChannelMap.put(new String(a, CharsetUtil.UTF_8), ctx.channel());

        System.out.println("接收到客户端信息：" + new String(a, CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress().toString());

        int rand = new Random().nextInt(10);
        log.info("随机值：{}", rand);

        // 1. 将耗时部分放入workGroup下的taskQueue队列中异步执行,可以放入多个任务
        // taskQueue中只有一个线程，多任务会阻塞执行
        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                log.info("taskQueue完成, {}", LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 2. 用户自定义定时任务,该任务不是提交到taskQueue而是 ScheduleTaskQueue中
        // ScheduleTaskQueue和taskQueue 不会阻塞执行，各自有一个线程
        ctx.channel().eventLoop().schedule(() -> {
                log.info("scheduleQueue完成, {}", LocalDateTime.now());
        }, 5, TimeUnit.SECONDS);

    }

    /**
     * 上面方法读取完毕后触发的事件，可以用于消息返回
     * @author lxq
     * @date 2021/4/25 15:09
     * @param ctx
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
     * @author lxq
     * @date 2021/4/10 16:42
     * @return null
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        removeConnect(ctx);
    }

    /**
     * 空闲事件
     * @author lxq
     * @date 2021/4/10 16:41
     * @param ctx
     * @param evt
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String socketString = ctx.channel().remoteAddress().toString();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("Client: " + socketString + " READER_IDLE 读空闲");
                disConnect(ctx);
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("Client: " + socketString + " WRITER_IDLE 写空闲");
                disConnect(ctx);
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.info("Client: " + socketString + " ALL_IDLE 总空闲");
                disConnect(ctx);
            }
        }
    }

    /**
     * 客户端断开连接
     * @author lxq
     * @date 2021/4/10 16:40
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("关闭通道");
        removeConnect(ctx);
    }


    /**
     * 删除连接,连接已经被关闭，删除通道
     * @author lxq
     * @date 2021/4/10 16:48
     * @param ctx
     */
    public void removeConnect(ChannelHandlerContext ctx){
        try {
            UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
            clients.remove(ctx.channel());
            ctx.close();
        } catch (Exception e) {
            log.error("删除链接异常！");
        }
    }

    /**
     * 断开连接,主动断开
     * @author lxq
     * @date 2021/4/10 16:48
     * @param ctx
     */
    public void disConnect(ChannelHandlerContext ctx){
        try {
            UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
            clients.remove(ctx.channel());
            ctx.disconnect();
        } catch (Exception e) {
            log.error("断开链接异常！");
        }
    }


}

