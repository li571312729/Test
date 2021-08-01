package com.concurrent.thread;

public class DaemonThread {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            for (; ; ) {
            }
        });

        thread.setDaemon(true);

        thread.start();

        System.out.println("main is over");
    }
}
