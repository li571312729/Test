package com.net.nio.talk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lxq
 * @date 2021年04月16日 16:21
 */
public class Server {

    public static void main(String[] args){

        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = null;
        // 创建Selector
        Selector selector = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();

            selector = Selector.open();

            // 绑定端口7002, 并进行监听
            serverSocketChannel.socket().bind(new InetSocketAddress(7002));

            // 设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);

            // 注册serverSocketChannel到selector，并且设置关心事件为 OP_ACCEPT
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            System.out.println("sockerServer error:");
            e.printStackTrace();
        }

        while (true){
            try {
                int select = selector.select(5000);
                if (select == 0){
                    System.out.println("时间:" + LocalDateTime.now() + " ,服务器等待了5秒，无连接...");
                    continue;
                }
            } catch (IOException e) {
                System.out.println("selector error:");
                e.printStackTrace();
            }


            // 获取所有关注的事件集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                // 如果该事件是请求建立连接事件
                if(selectionKey.isAcceptable()){
                    try {
                        // 因为已经获取到连接事件，因此这里了SerSocket不会阻塞
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 设置socketChannel为非阻塞
                        socketChannel.configureBlocking(false);
                        // SocketChannel注册到 selector上，并设置关注事件为写事件
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } catch (IOException e) {
                        System.out.println("socketChannel error:");
                        e.printStackTrace();
                    }
                }

                // 如果该事件是数据读取事件
                if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel)selectionKey.channel();

                    // 这里一次读不完下次，则 selector.select会捕捉到剩下的继续触发read事件，会再进入这里
                    // 可以考虑这里循环读完再出去，也可以下次进来继续读（不过这样总归不太好）
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    try {
                        channel.read(buffer);
                        System.out.println("客户端说：" + new String(buffer.array()));
                    } catch (IOException e) {
                        System.out.println("read Msg error:");
                        selectionKey.cancel();
                        try {
                            channel.close();
                        } catch (IOException ex) {
                            System.out.println("channel.close() error:");
                            ex.printStackTrace();
                        }
                    }
                }

                // 删除当前selectionKey，是因为多线程可能会重复操作
                iterator.remove();
            }
        }
    }
}

