package com.net.bio.threadpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lxq
 * @date 2021年05月20日 14:30
 */
public class TimeServerHandler implements Runnable{

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String body = null;
            while(true){
                body = in.readLine();
                if(body == null){
                    break;
                }
                System.out.println(LocalDateTime.now() + "---->client" + socket.getRemoteSocketAddress().toString() + ":" + body);
                out.println(System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Optional.ofNullable(socket).ifPresent(o -> {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

