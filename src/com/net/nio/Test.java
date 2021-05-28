package com.net.nio;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author lxq
 * @date 2021年04月16日 13:35
 */
public class Test {

    static CountDownLatch countDownLatch = null;

    static String B() {
        System.out.println("B()...");
        return "B";
    }

    public static void main(final String... args) {
        countDownLatch = new CountDownLatch(1);
        System.out.println(1111111111);
        try {
            countDownLatch.countDown();
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(22222222);
    }

}

