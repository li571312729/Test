package com.net.bio.socketCopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统IO通过socket进行拷贝文件
 */
public class Server {

    public static void main(String[] args) {

        try(
                ServerSocket serverSocket = new ServerSocket(8887);
        ) {
            while (true){
                Socket client = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
                byte[] data = new byte[4096];
                while (dataInputStream.read(data, 0 , data.length) > 0){
                    System.out.println(new String(data));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
