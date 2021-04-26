package com.net.nio.zeroCopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 基于NIO的零拷贝 2次切换上下文，2次拷贝
 */
public class Server {

    public static void main(String[] args) {

        try (
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ) {

            serverSocketChannel.socket().bind(new InetSocketAddress(8888));
            while (true) {
                try {
                    SocketChannel client = serverSocketChannel.accept();
                    ByteBuffer data = ByteBuffer.allocate(4096);

                    while (client.read(data) > 0) {
                        data.rewind();
                    }
                } catch (IOException e) {
                }
            }

        } catch (IOException e) {
        }
    }
}
