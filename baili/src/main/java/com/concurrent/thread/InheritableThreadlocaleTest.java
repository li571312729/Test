package com.concurrent.thread;

public class InheritableThreadlocaleTest {

    static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("hello");
        System.out.println("main thread : " + threadLocal.get());

        Thread threadA = new Thread(() -> {

            System.out.println("ThreadA : " + threadLocal.get());

            threadLocal.set("hello world");

            System.out.println("ThreadA : " + threadLocal.get());
        });

        threadA.start();
        threadA.join();
        System.out.println("main thread : " + threadLocal.get());
    }
}
