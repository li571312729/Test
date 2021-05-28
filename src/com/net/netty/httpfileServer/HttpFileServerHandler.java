package com.net.netty.httpfileServer;

import com.net.bio.talk.util.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpUtil.setContentLength;

/**
 * @author lxq
 * @date 2021年05月27日 16:09
 */
@Slf4j
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-za-z0-9][-_A-za-z0-9\\.]*");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (!request.decoderResult().isSuccess()) {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }

        if (request.method() != HttpMethod.GET) {
            sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }

        String uri = request.uri();
        String path = sanitizeUri(uri);
        if("/favicon.ico".equals(path)){
            return;
        }

        if (Utils.isNullOrEmpty(path)) {
            sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }

        File file = new File(path);
        if (file.isHidden() || !file.exists()) {
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }

        if (file.isDirectory()) {
            sendListing(ctx, file, uri);
        }

        if (!file.isFile()) {
            sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }

        RandomAccessFile randomAccessFile = null;
        try {
            // 以只读的方式打开文件
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }

        long fileLength = randomAccessFile.length();
        HttpResponse httpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        setContentLength(httpResponse, fileLength);
        httpResponse.headers().set(CONTENT_TYPE, "application/octet-stream");
        if (isKeepAlive(request)) {
            httpResponse.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.write(httpResponse);
        ChannelFuture future = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192), ctx.newProgressivePromise());
        ctx.write(LastHttpContent.EMPTY_LAST_CONTENT);
        ctx.flush();
        future.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {
                log.info("文件{}内容读取中,读取进度为{},总量为{}。", path, progress, total);
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                if (future.isDone()) {
                    if (future.isSuccess()) {
                        log.info("文件{}内容读取成功。", path);
                    } else {
                        log.info("文件{}内容读取失败:[{}]", path, future.cause());
                    }
                }
            }
        });

    }

    /**
     * 设置状态码
     * @author lxq
     * @date 2021/5/27 17:04
     * @param ctx
     * @param status
     */
    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.setStatus(status);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private String sanitizeUri(String uri) {
        try {
            uri = URLDecoder.decode(uri, CharsetUtil.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            try {
                uri = URLDecoder.decode(uri, CharsetUtil.ISO_8859_1.name());
            } catch (UnsupportedEncodingException ex) {
                throw new Error();
            }
        }

        uri = uri.replace('/', File.separatorChar);
        if (uri.contains(File.separator + '.')
                || uri.contains('.' + File.separator)
                || uri.startsWith(".")
                || uri.endsWith(".")) {
            return null;
        }
        return "E:" + uri;
    }

    private static void sendListing(ChannelHandlerContext ctx, File dir, String uri) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8");

        // 获取上一页路径
        String parent = uri.substring(0, uri.lastIndexOf('/'));
        if(Utils.isNullOrEmpty(parent) || "/".equals(parent)){
            parent = uri;
        }
        StringBuilder buf = new StringBuilder();
        buf.append("<!DOCTYPE html><html><body><ul>");
        buf.append("<li><a href=\"" + parent + "\">..</a></li>\r\n");
        for (File file : dir.listFiles()) {
            buf.append("<li><a href=\"");
            String s = file.getPath().substring(2).replaceAll("\\\\", "/");
            buf.append(s);
            buf.append("\">");
            buf.append(file.getName()).append("</a></li>\r\n");
        }
        buf.append("</ul></body></html>\r\n");
        ByteBuf byteBuf = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
        response.content().writeBytes(byteBuf);
        byteBuf.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}

