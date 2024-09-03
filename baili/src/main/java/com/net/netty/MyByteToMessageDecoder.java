package com.net.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author lxq
 * @date 2021年04月11日 15:06
 */
public class MyByteToMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() >= 3){
            byte[] versionBytes = new byte[2];
            in.readBytes(versionBytes);
            if("v1".equals(new String(versionBytes))){
                byte b = in.readByte();
                if(b == 0){
//                    out.add(Package.builder().version(new String(versionBytes)).msgType(b).build());
                }else if(b == 1 && in.readableBytes() >= 4){
//                    out.add(Package.builder().version(new String(versionBytes)).msgType(b).projectId(in.readInt()).build());
                }
            }
        }
    }
}

