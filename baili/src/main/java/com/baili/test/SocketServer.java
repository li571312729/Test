package com.baili.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) throws Exception {
		Socket socket = null;
		ServerSocket server = new ServerSocket(9002);
		while(true){
			socket = server.accept();
			try(
				BufferedReader	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				) {
				while (true) {
					String str = in.readLine();
					System.out.println("我是服务端，客户端说：" + str);
	//			out.write("你好!\n");
					out.write(str + "\n");
					out.flush();
				}
			} catch (IOException e) {
				System.out.println("客户端连接中断!!!!!");
			}
		}
	}
}
