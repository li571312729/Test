package com.concurrent.thread;

public class InterruptTest3 {

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            for (; ; ) {
            }
        });

        // 启动线程
        threadA.start();

        // 设置中断标志
        threadA.interrupt();

        // 获取中断标志
        System.out.println("isInterrupted: " + threadA.isInterrupted());

        // 获取中断标志并重置
        System.out.println("isInterrupted: " + threadA.interrupted());

        // 获取中断标志并重置
        System.out.println("isInterrupted: " + Thread.interrupted());

        // 获取中断标志
        System.out.println("isInterrupted: " + threadA.isInterrupted());

        threadA.join();

        System.out.println("main is over");

    }
}
