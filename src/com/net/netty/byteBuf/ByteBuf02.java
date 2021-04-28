package com.net.netty.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author lxq
 * @date 2021年04月28日 11:17
 */
public class ByteBuf02 {

    public static void main(String[] args) {

        // 根据现有数据创建buffer
        ByteBuf buffer = Unpooled.copiedBuffer("h,llo", CharsetUtil.UTF_8);

        // 容量是字符长度*编码形式最大值 （保证符合改编码中任何字符） 5 * 3，容量不一定全部占满
        System.out.println(buffer.capacity());

        // 从buffer中去寻找特殊的字符（ASC） 找不到下标就为 -1
        int i = buffer.forEachByte(ByteProcessor.FIND_COMMA);
         System.out.println(i);

        // 这个是返回数组中可以读取的字节数 ，，
        System.out.println(buffer.readableBytes());
        if(buffer.hasArray()){
            System.out.println(new String(buffer.array()));
        }


    }
    
}

