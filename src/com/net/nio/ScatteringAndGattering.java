package com.net.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author lxq
 * @date 2021年04月16日 13:42
 */
public class ScatteringAndGattering {
    public static void main(String[] args) throws Exception{

        // 创建ServerSocketChannel
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(7002);

        // 绑定端口7000到socket监听并启动
        server.socket().bind(address);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocateDirect(5);
        byteBuffers[1] = ByteBuffer.allocateDirect(3);

        // 等待客户端连接
        SocketChannel sockerChannel = server.accept();

        while (true){

            // buffer数组 ，这里会自动分散到相应的数组中各个buffer
            long read = sockerChannel.read(byteBuffers);
            Arrays.asList(byteBuffers).stream()
                    .map(buffer -> "position:" + buffer.position() + ",limit" + buffer.limit())
                    .forEach(System.out::println);

            // 翻转buffer的读写模式，这里是将buffer从读模式切换为写模式
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

            long write = sockerChannel.write(byteBuffers);

            // 将所有的buffer进行复位操作
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.clear());

            System.out.println("byteRead:" + read + ",\tbyteWrite" + write);
        }

        

    }
}

