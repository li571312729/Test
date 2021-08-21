package com.concurrent;

import java.util.concurrent.*;

/**
 * @author lxq
 * @date 2021年08月19日 9:53
 */
public class CountDownLatchTest1 {

    private static volatile CountDownLatch count = new CountDownLatch(2);


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> b_is_end = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("B is end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.countDown();
            }
            return "B";
        });

        Future<String> a_is_end = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("A is end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                count.countDown();
            }
            return "A";
        });

        count.await();
        System.out.println("main is end");
        executorService.shutdown();
    }



}
