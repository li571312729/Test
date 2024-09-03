package com.net.bio.talk;
 
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        System.out.println("服务器启动中...");
        //保存客户端处理的线程
        Vector<UserThread> vector = new Vector <>();
        //固定大小的线程池，用来处理不同客户端
        ExecutorService es = Executors.newFixedThreadPool(100);
        //创建服务器端的Socket
        try(
             ServerSocket server = new ServerSocket(8000);
        ) {
            System.out.println("服务器以启动，正在等待连接...");
            while(true){
                //接受客户端的Socket，若没有，阻塞在这
                Socket socket = server.accept();
                //每来一个客户端，创建一个线程处理它
                UserThread user = new UserThread(socket,vector);
                es.execute(user);  //开启线程
            }
        } catch (IOException e) {
            System.err.println("服务器启动失败");
            e.printStackTrace();
        }
    }
}