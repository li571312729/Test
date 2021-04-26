package com.proxy.dynamic;

import java.util.concurrent.TimeUnit;

public class Bicycle implements MoveAble {

    @Override
    public void run() {
        System.out.println("Bicycle gogogo...");
        try {
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void move() {
        System.out.println("movemove......");
    }

}
