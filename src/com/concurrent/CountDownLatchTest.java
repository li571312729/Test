package com.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @date 2021年08月19日 9:53
 */
public class CountDownLatchTest {

    private static volatile CountDownLatch count = new CountDownLatch(2);


    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("A is end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.countDown();
            }
        }, "A");

        Thread threadB = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("B is end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.countDown();
            }

        }, "B");

        threadA.start();
        threadB.start();
        count.await();
        System.out.println("main is end");

    }



}
