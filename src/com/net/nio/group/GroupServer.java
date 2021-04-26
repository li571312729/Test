package com.net.nio.group;

import com.net.bio.talk.util.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO socket群聊
 *
 * @author lxq
 * @date 2021年04月23日 13:46
 */
public class GroupServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private final Integer PORT = 9999;

    public GroupServer() {

        try {
            // 获取选择器
            selector = Selector.open();

            // 获取serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();

            //绑定监听端口
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

            // 设置阻塞模式为异步
            serverSocketChannel.configureBlocking(false);

            // 向selector注册，并监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听事件
     *
     * @author lxq
     * @date 2021/4/23 13:55
     */
    public void listen() {

        while (true) {
            try {
                int select = selector.select(5000);
                if (select == 0) {
                    continue;
                }
            } catch (IOException e) {
                System.out.println("selector error:");
                e.printStackTrace();
            }

            // 获取所有关注的事件集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                // 如果该事件是请求建立连接事件
                if (selectionKey.isAcceptable()) {
                    try {
                        // 因为已经获取到连接事件，因此这里了SerSocket不会阻塞
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 设置socketChannel为非阻塞
                        socketChannel.configureBlocking(false);
                        // SocketChannel注册到 selector上，并设置关注事件为写事件
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        System.out.println(socketChannel.getRemoteAddress().toString().substring(1) + "上线");
                    } catch (IOException e) {
                        System.out.println("socketChannel error:");
                        e.printStackTrace();
                    }
                }

                // 如果该事件是数据读取事件
                if (selectionKey.isReadable()) {
                    // 读取客户端消息，如果不为空则进行转发
                    String msg = readMsg(selectionKey);
                    if(Utils.notEmpty(msg)){
                        // 群发消息
                        sendDirect(msg, (SocketChannel) selectionKey.channel());
                    }
                }
            }
            // 删除当前selectionKey，是因为多线程可能会重复操作
            iterator.remove();
        }
    }

    /**
     * 读取通道消息
     *
     * @param selectionKey
     * @return
     */
    public String readMsg(SelectionKey selectionKey) {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
        try {
            int read = channel.read(buffer);
            // 一次读完后将buffer倒带，下次可以继续读，相当于每次读都是新的buffer,也可以每次创建一个
            buffer.rewind();
            StringBuffer msg = new StringBuffer();
            msg.append(new String(buffer.array()));
            while (read > 0){
                read = channel.read(buffer);
                msg.append(new String(buffer.array()));
                buffer.rewind();
            }
            if(Utils.notEmpty(msg.toString())){
                System.out.println(channel.getRemoteAddress().toString().substring(1) + "->客户端说：" + msg);
                return msg.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                System.out.println(channel.getRemoteAddress().toString().substring(1) + "离线了。");
                selectionKey.cancel();
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 群发消息，除了当前通道
     *
     * @param msg
     * @param channel 当前自身通道
     * @author lxq
     * @date 2021/4/23 13:57
     */
    public void sendDirect(String msg, SocketChannel channel) {
        // 获取所有注册的SelectionKey
        Iterator<SelectionKey> iterator = selector.keys().iterator();

        while (iterator.hasNext()) {

            SelectionKey next = iterator.next();

            //从当前SelectionKey中获取绑定的客户端通道
            SelectableChannel client = next.channel();
            if (client instanceof SocketChannel && client != channel) {
                // 获取当前通道绑定的缓冲区，如果没有绑定就进行创建
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                try {
                    ((SocketChannel) client).write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        System.out.println("send client Msg error, msg:" + msg + ", client:" + ((SocketChannel) client).getRemoteAddress().toString().substring(1));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new GroupServer().listen();
    }
}

