package com.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lxq
 * @date 2021年08月12日 10:13
 */
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            LockSupport.unpark(Thread.currentThread());

            while (!Thread.currentThread().isInterrupted()){
                System.out.println(111111111);
                LockSupport.park();
            }
        });
        threadA.start();

        TimeUnit.SECONDS.sleep(2);

        threadA.interrupt();
        //LockSupport.unpark(threadA);

        System.out.println("main over");
    }
}
