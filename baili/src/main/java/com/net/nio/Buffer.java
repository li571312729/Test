package com.net.nio;

import java.nio.IntBuffer;

/**
 * @author lxq
 * @date 2021年04月14日 16:57
 */
public class Buffer {

    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(5);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i * 2);
        }

        // buffer 读写模式的切换
        buffer.flip();

        while (buffer.hasRemaining()){
            // get会导致指针移动
            System.out.println(buffer.get());
        }

    }
}

