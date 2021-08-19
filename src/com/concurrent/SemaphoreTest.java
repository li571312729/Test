package com.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @date 2021年08月19日 11:46
 */
public class SemaphoreTest {

    // 这个0并不是计数的个数，对应的计数可以在acquire时指定
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread() + "  step1");
                semaphore.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread() + "  step1");
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        semaphore.acquire(1);
        System.out.println("main is end");
        executorService.shutdown();
    }


}
