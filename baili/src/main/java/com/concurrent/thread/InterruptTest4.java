package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class InterruptTest4 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

            while (!Thread.interrupted()) {
            }

            System.out.println("now  interrupted status : " + Thread.currentThread().isInterrupted());
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        thread.interrupt();

        thread.join();

        System.out.println("main is over");
    }
}
