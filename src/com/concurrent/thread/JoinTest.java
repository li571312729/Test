package com.concurrent.thread;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" A end");
        });

        Thread threadB = new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" B end");
        });

        threadA.start();
        threadB.start();

        System.out.println(" main must wait all thread over:");

        threadA.join();
        threadB.join();

        System.out.println(" main over");

    }
}
