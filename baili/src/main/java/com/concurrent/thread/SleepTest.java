package com.concurrent.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepTest {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            // 获取锁
            lock.lock();
            try {
                System.out.println("A begin sleep");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("A end sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            // 获取锁
            lock.lock();
            try {
                System.out.println("B begin sleep");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("B end sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        threadA.start();
        threadB.start();
    }
}
