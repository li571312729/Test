package com.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lxq
 * @date 2021年08月19日 10:59
 */
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
        System.out.println(Thread.currentThread() + "   current finish");
    });

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + "  step1");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + "  step2");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + "  step3");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + "  step1");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + "  step2");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + "  step3");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }


}
