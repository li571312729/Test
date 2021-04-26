package com.net.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lxq
 * @date 2021年04月15日 10:21
 */
public class NIOFIleChannelFileCopy {
    public static void main(String[] args) throws FileNotFoundException {
        try(
                FileInputStream inputStream = new FileInputStream("E:\\Speed.log");
                FileOutputStream outputStream = new FileOutputStream("E:\\copy.txt");
        ) {
            inputStream.getChannel().transferTo(0, inputStream.getChannel().size(), outputStream.getChannel());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(){
        {

            File file = new File("E:\\text.txt");
            try(
                    FileInputStream inputStream = new FileInputStream(file);
                    FileOutputStream outputStream = new FileOutputStream("E:\\copy.txt");
            ) {
                FileChannel channel = inputStream.getChannel();
                FileChannel channel1 = outputStream.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                // 循环读取,这里不建议直接使用ByteBuffer.allocate((int)file.length()));  可能精度丢失
                while (true){
                    buffer.rewind();
                    int read = channel.read(buffer);
                    System.out.println("read: " + read);
                    if(read == -1){
                        break;
                    }

                    // 因为buffer可能比文件内容小，所以读满buffer当然要写入通道一次,不然下次会覆盖
                    buffer.flip();
                    channel1.write(buffer);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}

