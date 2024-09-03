package com.angorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class 除数博弈 {

    private static volatile ConcurrentHashMap<String, List<String>>
            websocketMap = new ConcurrentHashMap<>(16);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            List<String> webSocketScreens = websocketMap.get("test");
            if (null == webSocketScreens) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                }
                synchronized (除数博弈.class) {
                    // 这里需要重新获取再判断
                    webSocketScreens = websocketMap.get("test");
                    System.out.println(Thread.currentThread().getName() + "" + (null == webSocketScreens));
                    if (null == webSocketScreens) {
                        webSocketScreens = new ArrayList<>(10);
                        websocketMap.put("test", webSocketScreens);
                        System.out.println(Thread.currentThread().getName()
                                + "初始化完成");
                    }
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            List<String> webSocketScreens = websocketMap.get("test");
            if (null == webSocketScreens) {
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                }
                synchronized (除数博弈.class) {
                    // 这里需要重新获取再判断
                    webSocketScreens = websocketMap.get("test");
                    System.out.println(Thread.currentThread().getName() + "" + (null == webSocketScreens));
                    if (null == webSocketScreens) {
                        webSocketScreens = new ArrayList<>(10);
                        websocketMap.put("test", webSocketScreens);
                        System.out.println(Thread.currentThread().getName()
                                + "初始化完成");
                    }
                }
            }
        }, "thread2");

        thread1.start();
        thread2.start();

        Thread.sleep(2000);
    }


    /**
     *
     爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     选出任一 x，满足 0 < x < N 且 N % x == 0 。
     用 N - x 替换黑板上的数字 N 。
     如果玩家无法执行这些操作，就会输掉游戏。
     只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 False。假设两个玩家都以最佳状态参与游戏
     */
    public void divisorGame(String test) {

    }



}
