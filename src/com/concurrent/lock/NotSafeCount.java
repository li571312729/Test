package com.concurrent.lock;

import lombok.val;

/**
 * @author lxq
 * @date 2021年08月03日 14:10
 */
public class NotSafeCount {

    private static long value = 0;

    public static synchronized long getValue() {
        return value;
    }

    public static synchronized void inic(){
        ++value;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                inic();
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                inic();
            }
        }, "threadB");

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(Thread.currentThread().getName() + " : " + getValue());
    }


}
