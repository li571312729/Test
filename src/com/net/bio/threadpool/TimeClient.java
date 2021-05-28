package com.net.bio.threadpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author lxq
 * @date 2021年05月20日 14:35
 */
public class TimeClient {

    public static void main(String[] args) {

       try (
           Socket socket = new Socket("127.0.0.1", 8888);
           BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
       ){
           out.println("hello I am client");
           System.out.println("Server:" + in.readLine());
           Scanner scanner = new Scanner(System.in);
           while(scanner.hasNextLine()) {
               String s = scanner.nextLine();
               out.println(s);
               System.out.println("Server:" + in.readLine());
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}

