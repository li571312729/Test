package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class DaemonThread {

    public static void main(String[] args) throws Exception{


        Thread thread = new Thread(() -> {
            // 如果被中断，则重置中断标识并退出
            while(!Thread.interrupted()){

            }
            System.out.println("thread end");
        });

//        thread.setDaemon(true);

        thread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 通过中断通知其他线程
            thread.interrupt();
            System.out.println("JVM 要退出了");

        }));

        TimeUnit.SECONDS.sleep(2);
        System.out.println("main is over");
        System.exit(0);
    }

}
