package com.net.bio.socketCopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

/**
 * 传统IO通过socket进行拷贝文件
 */
public class Client {

    public static void main(String[] args) {

        try(
                Socket socket = new Socket("127.0.0.1", 8887);

                FileInputStream fileInputStream = new FileInputStream("E:\\Speed.log");

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            ) {
            int readCount = 0;
            BigInteger total = BigInteger.valueOf(0);
            byte[] bytes = new byte[4096];
            long t = System.currentTimeMillis();

            while ((readCount = fileInputStream.read(bytes, 0, bytes.length)) > 0){
                total = total.add(BigInteger.valueOf(readCount));
                dataOutputStream.write(bytes);
            }

            System.out.println("发送字节数为：" + total.toString() + ",总耗时为：" + (System.currentTimeMillis() - t));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
