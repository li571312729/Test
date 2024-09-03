package com.net.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

/**
 * SimpleChannelInboundHandler是 ChannelInboundHandlerAdapter的子类
 * HttpObject表示客户端和服务器端相互通信的数据被封装成HttpObject
 *
 * @author lxq
 * @date 2021年04月26日 11:28
 */
@Slf4j
public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取事件触发
     *
     * @param channelHandlerContext
     * @param httpObject
     * @author lxq
     * @date 2021/4/26 13:45
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        // 判断消息是不是HttpRequest请求
        if (httpObject instanceof HttpRequest) {
            log.info("客户端地址：{}", channelHandlerContext.channel().remoteAddress());

            // 获取到httObject中包含的请求资源（网页图标）, 演示对特定资源进行过滤
            URI uri = new URI(((HttpRequest) httpObject).uri());
            if("/favicon.ico".equals(uri.getPath())){
                return;
            }

            // 回复消息
            ByteBuf data = Unpooled.copiedBuffer("hello 我是服务器", CharsetUtil.UTF_8);

            //  构造一个http的响应，即httpResponse
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, data);
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, data.readableBytes());

            // 将构造好的http response返回
            channelHandlerContext.writeAndFlush(defaultFullHttpResponse);
        }
    }

}

