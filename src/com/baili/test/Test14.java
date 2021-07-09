package com.baili.test;

import java.util.concurrent.*;

/**
 * @author lxq
 * @date 2021年04月22日 13:59
 */
public class Test14 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor;
        /**
         * 核心线程数
         */
        int corePoolSize = 50;
        /**
         * 最大线程数
         */
         int maximumPoolSize = 200;
        /**
         * 超时时间30秒
         */
         long keepAliveTime = 30;

        // 采用默认的线程工厂和默认的拒绝策略
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());

        Future<Integer> submit = threadPoolExecutor.submit(() -> {
            return 123;
        });
        Integer integer = submit.get();
        System.out.println("integer:" + integer);

        Future<Integer> submit1 = threadPoolExecutor.submit(() -> {
            return 123;
        });
        Integer a = submit1.get();
        System.out.println("a:" + a);
    }
}

