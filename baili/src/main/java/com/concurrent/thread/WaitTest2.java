package com.concurrent.thread;

/**
 * 当前线程调用共享变量的wait()方法后只会释放当前共享变量上的锁，如果当前线程还持有其他共享变量的锁
 * ，则这些锁是不会被释放的。
 * @author lxq
 * @date 2021年07月30日 16:17
 */
public class WaitTest2 {

    static volatile Object resourceA = new Object();
    static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("A 获取到资源A的锁。");
                System.out.println("A 现在开始尝试获取资源B的锁。");
                synchronized (resourceB) {
                    System.out.println("A 获取到资源B的锁。");
                    System.out.println("A 释放资源A。");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("B 获取到资源A的锁。");
                System.out.println("B 现在开始尝试获取资源B的锁。");
                synchronized (resourceB) {
                    System.out.println("B 获取到资源B的锁。");
                    System.out.println("B 释放资源A。");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
    }

}
