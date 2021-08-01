package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class SleepAndInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {

            System.out.println("A begin sleep");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("A end sleep");
        });

        threadA.start();

        TimeUnit.SECONDS.sleep(1);

        threadA.interrupt();
    }
}
