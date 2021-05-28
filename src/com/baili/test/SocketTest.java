package com.baili.test;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class SocketTest {

	public static void main(String[] args) throws Exception {
		byte[] msg = new byte[7];
		// 版本号
		byte[] bytes = "v1".getBytes();
		// 消息类型
		byte msg_type = 1;
		// 违规类型
//		byte[] accidentTypeId = doubleToBytes_Big(12);
		// 违规人数
		byte[] accidentNum = intToByte4B(4);
		// 摄像头编号
		byte[] cameraId = "zFvaqQ".getBytes();

		System.arraycopy(bytes, 0, msg, 0, bytes.length);
		msg[2] = msg_type;
        System.arraycopy(intToByte4B(21), 0, msg, 3, 4);

//		System.arraycopy(accidentTypeId, 0, msg, 7, accidentTypeId.length);
//		System.arraycopy(accidentNum, 0, msg, 15, accidentNum.length);
//		System.arraycopy(cameraId, 0, msg, 19, cameraId.length);


		Socket socket = new Socket("127.0.0.1",9003);
        InputStream inputStream = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        BufferedWriter out = new BufferedWriter(outputStreamWriter);


        socket.getOutputStream().write(msg);
		//out.write("123");
		out.flush();
		while (true) {
            byte[] readbuf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len = inputStream.read(readbuf);

            sb.append(new String(readbuf,0,len));

            System.out.println("message from server:"+sb.toString());


//            String str = br.readLine();
//			System.out.println("我是客户端，服务器说：" + str);
//			out.write("你好!\n");
//			out.flush();
//			TimeUnit.SECONDS.sleep(2);
		}
	}


    /**
     * long转byte数组，大端模式
     * @param l
     * @return
     */
    public static byte[] doubleToBytes_Big(long l){
        byte b[] = new byte[8];
        b[0] = (byte)  (0xff & (l >> 56));
        b[1] = (byte)  (0xff & (l >> 48));
        b[2] = (byte)  (0xff & (l >> 40));
        b[3] = (byte)  (0xff & (l >> 32));
        b[4] = (byte)  (0xff & (l >> 24));
        b[5] = (byte)  (0xff & (l >> 16));
        b[6] = (byte)  (0xff & (l >> 8));
        b[7] = (byte)  (0xff & l);
        return b;
    }

    /**
     * int整数转换为4字节的byte数组
     *
     * @param i  整数
     * @return byte数组
     */
    public static byte[] intToByte4B(int i) {
        byte[] b = new byte[4];
        b[0] = (byte) (i >> 24 & 0xff); //数据组起始位,存放内存起始位, 即:高字节在前
        b[1] = (byte) (i >> 16 & 0xff); //高字节在前是与java存放内存一样的, 与书写顺序一样
        b[2] = (byte) (i >> 8 & 0xff);
        b[3] = (byte) (i & 0xff);
        return b;
    }
}
