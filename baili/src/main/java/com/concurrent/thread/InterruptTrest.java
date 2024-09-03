package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class InterruptTrest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("i am fine");
            }
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(1000);

        System.out.println("main begin interrupt thread");

        thread.interrupt();

        thread.join();
        System.out.println("main is over");
    }
}
