package com.net.nio.group;

import com.net.bio.talk.util.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lxq
 * @date 2021年04月23日 16:34
 */
public class GroupClient {

    private Selector selector;
    private SocketChannel socketChannel;
    private final Integer PORT = 9999;
    private final String HOST = "127.0.0.1";
    private String userName;
    private static ExecutorService thread = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        GroupClient groupClient = new GroupClient();
        thread.execute((() -> {
            groupClient.readMsg();
        }));

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            groupClient.sendMsg(s);
        }
    }

    public GroupClient() {

        try {
            // 获取选择器
            selector = Selector.open();

            // 获取socketChannel
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));

            // 设置阻塞模式为异步
            socketChannel.configureBlocking(false);

            // 注册selector，兵监听读事件
            socketChannel.register(selector, SelectionKey.OP_READ);

            userName = socketChannel.getLocalAddress().toString().substring(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @author lxq
     * @date 2021/4/23 17:18
     * @param msg 
     */
    public void sendMsg(String msg){
        try {
            msg = userName + "->" + msg;
            ByteBuffer data = ByteBuffer.wrap(msg.getBytes(Charset.forName("UTF-8")));
            socketChannel.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取服务器消息
     * @author lxq
     * @date 2021/4/23 17:19
     */
    public void readMsg(){
        try {
            while (true){
                int select = selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer data = ByteBuffer.allocate(1024);
                        int read = channel.read(data);
                        StringBuffer msg = new StringBuffer();
                        while (read > 0){
                            msg.append(new String(data.array()));
                            // 这里要重置一下标记位，因为下面读完后，position到达了5,而limit也是5 如果不重置一下position 那么下次读的就是0
                            data.clear();
                            read = channel.read(data);
                        }

                        if(Utils.notEmpty(msg.toString())){
                            System.out.println(msg.toString());
                        }
                    }
                    iterator.remove(); //这里清除一下，防止多线程误操作
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

