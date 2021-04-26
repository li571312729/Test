package com.net.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lxq
 * @date 2021年04月15日 9:06
 */
public class NIOFileChannel {

    public static void main(String[] args) {

        String data = "0123Hello,NIO以及Netty!";
        try(
                FileOutputStream file = new FileOutputStream("F:\\text.txt");
        ) {
            // 获取文件通道
            FileChannel channel = file.getChannel();

            // 创建和缓冲区，大小为写入数据大小
            ByteBuffer buffer = ByteBuffer.allocate(data.getBytes("utf-8").length);

            buffer.put(data.getBytes("utf-8"));

            // 转换缓冲区读写模式，上面是放入缓冲区，现在要读出到通道
            buffer.flip();

            channel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

