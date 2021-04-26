package com.image;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Lixq
 *
 */
public class SensorDataReceive {
    
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ConcurrentLinkedQueue<Point> queue = new ConcurrentLinkedQueue<>();
    private static TransUtil transUtil;
    private static ConcurrentLinkedQueue<Point> allQueue = new ConcurrentLinkedQueue<>();
    private ImageTrajectory imageTrajectory = new ImageTrajectory(new File("D://image.png"));
    // socket 端口 
    private final int PORT = 9999;
    // 请求队列最大长度 
    private final int BACKLOG = 100;
    
    public static void main(String[] args) {
        //初始化地图的两个点（经纬度，像素）
        transUtil = new TransUtil(new Point(116.514051, 39.831447),new Point(116.533454, 39.820143),
                new Point(232, 206),new Point(381, 320));
        SensorDataReceive sReceive = new SensorDataReceive();
        sReceive.initConsumerThread();
        sReceive.startServer();
        sReceive.initResultThread();
    }
    
    /**
     * 队列消费数据
     */
    private void initConsumerThread() {
        new Thread(() -> {
            AtomicInteger fileNum = new AtomicInteger(0);
            while(true) {
                if(!queue.isEmpty()){
                    Point point = queue.peek();
                    try {
                        //处理逻辑 主要内容
                        //获取经纬度获取像素比例
                        transUtil.getLLPixelScale();
                        //获取经纬度像素坐标
                        ArrayList<Point> arrayList = new ArrayList<>();
                        arrayList.add(point);
                        List<Point> pixelCoordinates = transUtil.getLLCoordinate(arrayList);
                        /**使用   经纬度  end **/
                        int[] xPointArrs = new int[pixelCoordinates.size()];
                        int[] yPointArrs = new int[pixelCoordinates.size()];

                        for(int i = 0; i < pixelCoordinates.size(); i ++){
                            xPointArrs[i] = (int) pixelCoordinates.get(i).getX();
                            yPointArrs[i] = (int) pixelCoordinates.get(i).getY();
                            System.out.println("X:" + xPointArrs[i] + ",Y:" + yPointArrs[i]);
                        }
                        imageTrajectory.trajectoryDrawing(xPointArrs, yPointArrs, String.valueOf(fileNum.incrementAndGet()));
                        queue.poll();
                        allQueue.add(point);
                        System.out.println("该点位信息绘制成功: " + point);
                    } catch (Exception e) {
                    }
                }
            }
        }, "image trajectory").start();
    }
    
    /**
     * 绘制所有点位的一个地图信息
     */
    private void initResultThread() {
        new Thread(() -> {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (Exception e1) {}
                
                ArrayList<Point> arrayList = new ArrayList<>();
                while(!allQueue.isEmpty()){
                    arrayList.add(queue.poll());
                }
                
                try {
                    //处理逻辑 主要内容
                    //获取经纬度获取像素比例
                    transUtil.getLLPixelScale();
                    //获取经纬度像素坐标
                    
                    List<Point> pixelCoordinates = transUtil.getLLCoordinate(arrayList);
                    /**使用   经纬度  end **/
                    int[] xPointArrs = new int[pixelCoordinates.size()];
                    int[] yPointArrs = new int[pixelCoordinates.size()];

                    for(int i = 0; i < pixelCoordinates.size(); i ++){
                        xPointArrs[i] = (int) pixelCoordinates.get(i).getX();
                        yPointArrs[i] = (int) pixelCoordinates.get(i).getY();
                        System.out.println("X:" + xPointArrs[i] + ",Y:" + yPointArrs[i]);
                    }
                    imageTrajectory.trajectoryDrawing(xPointArrs, yPointArrs, "Result");
                    System.out.println("绘制当前所有点位轨迹图。");
                } catch (Exception e) {
                }
                
        }, "image trajectory result").start();
    }

    /**
     * startServer 开启监听服务
     */
    @SuppressWarnings("resource")
    public void startServer(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT, BACKLOG);
            System.out.println("服务端已开启监听：");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        while(true){
            try {
                Socket socket = serverSocket.accept();
                //上一次返回GPS的时间点,各个线程之间独立计算
                Thread thread = new SocketAnalysis(socket, "socket analysis thread");
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
    }
   
    class SocketAnalysis extends Thread{

        private Socket socket;
        
        public SocketAnalysis(Socket socket, String name) {
            super(name);
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try(
                    InputStream is = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
               ){
                String sensorData = null;  
                while((sensorData = br.readLine()) != null){
                    System.out.println(simpleDateFormat.format(new Date()) + ",接收到传感器数据： " + sensorData);
                    // 解析GPS数据
                    analysisData(sensorData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(socket != null && !socket.isClosed()) {
                    try {
                        socket.close();
                    } catch (IOException e) {}
                }
            }
        }

        /**
         * @param sensorData
         * @return
         * 分析数据
         */
        private void analysisData(String sensorData) {
            JSONObject json = null;
            try {
                json = JSONObject.parseObject(sensorData);
                if(!(json.containsKey("longitude") && json.containsKey("latitude"))){
                    return;
                }
                queue.add(new Point( Double.parseDouble(json.getString("longitude")), Double.parseDouble(json.getString("latitude"))));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("GPS数据解析错误： " + sensorData);
            }
        }
    }
}
