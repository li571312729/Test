package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 当一个线程调用某个共享变量的wait（）方法被阻塞挂起后，如果其他线程中断了该线程，则线程会
 * 抛出InterruptException异常并返回
 * @author lxq
 * @date 2021年07月30日 17:44
 */
public class WaitAndInterrupt {

    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            synchronized (object) {
                System.out.println("获取到资源锁。");
                try {
                    object.wait();
                    System.out.println("线程被唤醒。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();
    }




}
