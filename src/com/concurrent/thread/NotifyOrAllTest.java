package com.concurrent.thread;

import java.util.concurrent.TimeUnit;

public class NotifyOrAllTest {

    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            synchronized (resourceA){
                System.out.println(" A get resourceA");
                System.out.println(" A bengin wait");
                try {
                    resourceA.wait();
                    System.out.println("A end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceA){
                System.out.println(" B get resourceA");
                System.out.println(" B bengin wait");
                try {
                    resourceA.wait();
                    System.out.println("B end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            synchronized (resourceA){
                System.out.println(" C begin notifyOrAll");
                resourceA.notifyAll();
            }
        });

        threadA.start();
        threadB.start();

       // TimeUnit.SECONDS.sleep(1);
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");
    }




}
