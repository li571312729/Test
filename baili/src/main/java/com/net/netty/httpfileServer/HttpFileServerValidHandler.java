package com.net.netty.httpfileServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lxq
 * @date 2021年05月28日 11:15
 */
@Slf4j
public class HttpFileServerValidHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 传递给下一个相应的通道处理程序
        ctx.fireChannelRead(msg);
    }
}

