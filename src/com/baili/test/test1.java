package com.baili.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.commons.net.telnet.TelnetClient;

public class test1 {
	
	public static void main(String[] args) throws IOException{
	    String a = "XML";
        byte[] bytes = a.getBytes();
        Socket socket = null;
	        try {
	            socket = new Socket("192.168.1.63", 19204);
	            System.out.println("端口通");
	        } catch (IOException e) {
	            e.printStackTrace();
	            if(socket != null)
	                socket.close();
	            System.out.println("端口不通");
	        }// 监听端口
	        
	       TelnetClient telnet = new TelnetClient();
	       try {
            telnet.connect("192.168.1.63", 64425);
            System.out.println("端口通");
	       } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("端口不通");
	       }
	}
	
	public static void processRunningCheck() {
        BufferedReader bufferedReader = null;
        try {
            Process proc = Runtime.getRuntime().exec("cmd /c telnet 192.168.63 19234");
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);        
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                }
            }
        }
    }

}
