package com.net.nio.zeroCopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 基于NIO的零拷贝 2次切换上下文，2次拷贝
 */
public class Client {

    public static void main(String[] args) {

        try(
                SocketChannel socketChannel = SocketChannel.open();
                FileInputStream fileInputStream = new FileInputStream("E:\\Speed.log");
            ) {
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
            FileChannel channel = fileInputStream.getChannel();

            long l1 = System.currentTimeMillis();

            // 注意这个方法在linux下调用一次即可，
            // 但是在windows下一次只能传输8M文件，所以windows下需要分多次传输，用 传输总长度/(8*1024*1024) 分次传输，
            // 并且position记录每次开始传输的位置
            long l = channel.transferTo(0, channel.size(), socketChannel);

            System.out.println("发送字节数为：" + channel.size() + ",总耗时为：" + (System.currentTimeMillis() - l1));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
