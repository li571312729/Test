package com.net.netty.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author lxq
 * @date 2021年04月28日 11:17
 */
public class ByteBuf01 {

    public static void main(String[] args) {

        //创建包含 长度为10 的byte[]对象
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < buffer.capacity(); i++ ){
            buffer.writeByte(i);
        }

        // netty的ByteBuf 和 nio中提供的ByteBuffer不同，这里不需要进行反转。
        // 主要是采用了 writeIndex和readIndex 分别控制读写来实现
        for (int i = 0; i < buffer.capacity(); i++ ){
            // readByte会使得readIndex指针后移
            System.out.println(buffer.readByte());;
        }

    }
    
}

