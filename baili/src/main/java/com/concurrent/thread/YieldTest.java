package com.concurrent.thread;

public class YieldTest {

    public static void main(String[] args) {
        new Yield();
        new Yield();
        new Yield();
    }
}

class Yield implements Runnable{

    public Yield() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if(i % 5 == 0){
                System.out.println(Thread.currentThread().getName() + " yield cpu");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + " over");
    }
}
