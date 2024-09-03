package com.net.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lxq
 * @date 2021年04月15日 9:52
 */
public class NIOFilechannelRead {
    public static void main(String[] args) {
        File file = new File("F:\\text.txt");
        try(
                FileInputStream fileInputStream = new FileInputStream(file);
        ) {
            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

            channel.read(buffer);

            buffer.flip();

            System.out.println(new String(buffer.array()));
//            while (buffer.hasRemaining()){
//                byte[] data = new byte[(int) file.length()];
//                buffer.get(data);
//                System.out.println(new String(data));
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

