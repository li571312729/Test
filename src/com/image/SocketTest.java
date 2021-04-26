package com.image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SocketTest {

    public static void main(String[] args) {

//                        List<Point> list = new ArrayList<>();
//                        list.add(new Point(116.423358,39.929454));
//                        list.add(new Point(116.423874,39.927807));
//                        list.add(new Point(116.426129,39.92796));
//                        list.add(new Point(116.425626,39.929288));
//
//                        list.add(new Point(116.424139,39.92909));
//                        list.add(new Point(116.423663,39.928921));
//                        list.add(new Point(116.423686,39.928561));
//                        list.add(new Point(116.423488,39.929118));
//                        list.add(new Point(116.424234,39.928845));

            try (
                    //客户端
                    //1、创建客户端Socket，指定服务器地址和端口
                    Socket socket =  new Socket("localhost", 9999);
                    //2、获取输出流，向服务器端发送信息
                    OutputStream os = socket.getOutputStream();//字节输出流
                    PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流
                    InputStream is = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                ){
                List<String> param = new ArrayList<>();
                param.add("{'longitude':'116.514051','latitude':'39.831447'}");
                param.add("{'longitude':'116.533454','latitude':'39.820143'}");
                param.add("{'longitude':'116.562658','latitude':'39.820347'}");
                param.add("{'longitude':'116.539374','latitude':'39.807267'}");
                param.add("{'longitude':'116.531325','latitude':'39.807156'}");
                param.add("{'longitude':'116.500998','latitude':'39.807489'}");
                param.add("{'longitude':'116.493237','latitude':'39.833757'}");

                param.forEach(o -> {
                    pw.println(o);
                    pw.flush();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {}
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}