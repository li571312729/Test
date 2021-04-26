package com.proxy.statics;

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

}
