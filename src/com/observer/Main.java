package com.observer;

import java.util.concurrent.TimeUnit;

public class Main {
    
    public static void main(String[] args) {
        DataCenter dc = new DataCenter("琅琊DC", false);
        dc.addListener(o -> {
            DataCenter dCenter = (DataCenter) o.getResource();
            System.err.println("路人视角 : " + dCenter.getName() + o.getMsg() + "开始拍照。");
        }).addListener(o -> {
            DataCenter dCenter = (DataCenter) o.getResource();
            System.err.println("消防队员：" + dCenter.getName() + o.getMsg() + "开始救火。");
        });
        new Thread(() -> {
            for(;;){
                if(!dc.isFire()){
                    System.out.println(dc.getName() + "情况正常，");
                }else{
                    System.err.println(dc.getName() + "着火了，赶紧人救火啊。");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                }
            }
        }, "背景").start();
        
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
        }
        dc.fire();
    }
}
