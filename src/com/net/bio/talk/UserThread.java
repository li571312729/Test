package com.net.bio.talk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class UserThread implements Runnable {
    
    private String name; //客户端的用户名称，唯一
    private Socket socket;
    private Vector<UserThread> vector;   //客户端处理线程的集合
    private ObjectInputStream oIn;    //输入流
    private ObjectOutputStream oOut;  //输出流
    private boolean flag = true;  //标记

    public UserThread(){};

    public UserThread(Socket socket, Vector<UserThread> vector) {
        this.socket = socket;
        this.vector = vector;
        vector.add(this);   //把当前客户线程加入集合 
    }
    
    @Override
    public void run() {
        try {
            //1、构造输入输出流
            System.out.println("客户端：" + socket.getInetAddress().getHostAddress() + "已连接！");
            oIn = new ObjectInputStream(socket.getInputStream());
            oOut = new ObjectOutputStream((socket.getOutputStream()));
            //2、循环读取
            while(flag){
                //读取消息对象
                Message message = (Message)oIn.readObject();
                //获取消息类型，登录还是发送消息
                int type = message.getType();
                //3、判断
                switch (type){
                    //如果是发送消息
                    case Constants.TYPE_SEND:
                        String to = message.getTargetName();//发送给谁
                        UserThread ut;
                        //遍历vector，找到接收信息的客户端
                        int size = vector.size();
                        for (int i = 0; i < size; i++) {
                            ut = vector.get(i);
                            //如果名字相同，且不是自己，就把信息发给它
                            if(to.equals(ut.name) && ut != this){
                                ut.oOut.writeObject(message); //发送消息对象
                            }
                        }
                        break;
                    //如果是登录
                    case Constants.TYPE_LOGIN:
                        this.name = message.getUserName();//获取用户名
                        message.setContent("欢迎您！");//设置登录成功信息
                        oOut.writeObject(message);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Vector<UserThread> getVector() {
        return vector;
    }

    public void setVector(Vector<UserThread> vector) {
        this.vector = vector;
    }

    public ObjectInputStream getoIn() {
        return oIn;
    }

    public void setoIn(ObjectInputStream oIn) {
        this.oIn = oIn;
    }

    public ObjectOutputStream getoOut() {
        return oOut;
    }

    public void setoOut(ObjectOutputStream oOut) {
        this.oOut = oOut;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
