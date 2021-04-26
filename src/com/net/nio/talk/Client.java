package com.net.nio.talk;

import com.net.bio.talk.util.Utils;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @date 2021年04月16日 17:26
 */
public class Client {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        if(!socketChannel.connect(new InetSocketAddress("127.0.0.1", 7002))){

            while (!socketChannel.finishConnect()){
                System.out.println("客户端没有连接成功。。。");
                TimeUnit.SECONDS.sleep(1);
            }
        }

        // wrap 直接根据你的字节数组大小自动分配，以前allocate太笨拙了
        ByteBuffer buffer = ByteBuffer.allocate(5);
        buffer.put("hello".getBytes());
        buffer.flip();
        socketChannel.write(buffer);

        executorService.submit(() -> {
            while (true){
                ByteBuffer data = ByteBuffer.allocate(1024);
                int read = socketChannel.read(data);
                StringBuffer msg = new StringBuffer();
                msg.append(new String(data.array()));
                while (read != -1 && read !=0){
                    // 这里要重置一下标记位，因为下面读完后，position到达了5,而limit也是5 如果不重置一下position 那么下次读的就是0
                    buffer.clear();
                    read = socketChannel.read(data);
                    msg.append(new String(data.array()));
                }
                if(Utils.notEmpty(msg.toString())){
                    System.out.println("服务器说：" + msg.toString());
                }
            }
        });

        while (true){
            Scanner s = new Scanner(System.in);
            String next = s.nextLine();
            System.out.println("客户端准备发送：" + next);
            // wrap 直接根据你的字节数组大小自动分配，以前allocate太笨拙了
            ByteBuffer sendData = ByteBuffer.allocate(next.getBytes().length);
            sendData.put(next.getBytes());
            sendData.flip();
            socketChannel.write(sendData);
        }
    }
}

