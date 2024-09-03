package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class InterruptTest2 {

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {

            System.out.println("A start sleep");

            try {
                TimeUnit.SECONDS.sleep(100000);
            } catch (InterruptedException e) {
                System.out.println("interrupt with sleep");
                return;
            }

            System.out.println("A is over");
        });

        threadA.start();

        TimeUnit.SECONDS.sleep(1);

        threadA.interrupt();

        threadA.join();

        System.out.println("main is over");
    }
}
