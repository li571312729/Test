package com.baili.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test2 {

	public static void main(String[] args) {
	    Object o = new Object();
	    try {
            aa();
        } catch (IOException e) {
            System.out.println("出来了");
        }
	}

	public static void aa() throws IOException{
	    Socket socket = null;
        try {
            socket = new Socket("192.168.1.5", 1);
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            throw e;
        } finally {
            System.out.println("finally");
            if(socket != null && !socket.isClosed()){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
