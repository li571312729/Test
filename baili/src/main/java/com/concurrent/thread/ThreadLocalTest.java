package com.concurrent.thread;

public class ThreadLocalTest {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();

    public static void print(){
        System.out.println(Thread.currentThread().getName() + " threadLocal value: " + threadLocal.get());
        threadLocal.remove();
    }

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {

            threadLocal.set("I am threadA");
            print();

            System.out.println("threadA after remove : " + threadLocal.get());
        }, "threadA");

        Thread threadB = new Thread(() -> {
            threadLocal.set("I am threadB");

            print();

            System.out.println("threadB after remove : " + threadLocal.get());
        }, "threadB");

        threadA.start();
        threadB.start();
    }
}
