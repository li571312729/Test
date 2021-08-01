package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class JoinAndInterrupt {

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {

            System.out.println("A begin");
            for (; ; ) {
            }
        });

        Thread mainThread = Thread.currentThread();

        Thread threadB = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mainThread.interrupt();
        });

        threadA.start();

        threadB.start();

        try {
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
