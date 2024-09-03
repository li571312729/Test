package com.net.bio.threadpool;

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServer {


    public static void main(String[] args) {
        try(
                ServerSocket serverSocket = new ServerSocket(8888);
            ) {
            Socket socket = null;
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                    1, 2,
                    3600, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(1));
            while (true){
                socket = serverSocket.accept();
                System.out.println(LocalDateTime.now() + ":新的连接接入：" + socket.getRemoteSocketAddress());
                threadPoolExecutor.execute(new TimeServerHandler(socket));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}