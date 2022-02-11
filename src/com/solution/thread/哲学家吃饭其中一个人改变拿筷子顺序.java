package com.solution.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * n个人n只筷子，围成一圈,只有拿到两只筷子，才能吃饭
 * 不加限制的情况下 运行一段时间就会死锁
 * 但是如果我们设置是其中一个人拿筷子的顺序跟其他人不同，则至少空出来一个能凑对的筷子，就不会产生死锁
 * @author xiaoqiangli
 * @Date 2022-02-11
 */
@Slf4j
public class 哲学家吃饭其中一个人改变拿筷子顺序 implements Runnable{

    // 哲学家编号
    private int id;

    // 筷子个数/人数
    private static final int number = 5;

    private static Semaphore[] forks = new Semaphore[number];  // 表示5支筷子的锁

    static {
        for (int i = 0; i < number; i++) {
            forks[i] = new Semaphore(1);  // 表示每支筷子最多被一个人同时使用
        }
    }

    public 哲学家吃饭其中一个人改变拿筷子顺序(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while(true){
                thinking();
                eat(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eat(int id) throws InterruptedException {
        // 随便找一个人让他拿筷子的顺序跟别人不同即可,这里我们找第一个就行
        if(id == 0){
            // 先拿右边边的筷子
            forks[(id + 4) % 5].acquire();

            // 然后拿左边边的筷子 因为是围成一圈可以得出左右筷子的编号规律
            forks[id].acquire();
       }else {
            // 先拿左边的筷子
            forks[id].acquire();

            // 然后拿右边的筷子 因为是围成一圈可以得出左右筷子的编号规律
            forks[(id + 4) % 5].acquire();
        }

        // 吃一口饭
        log.info("哲学家{}正在吃饭~", id);

        // 依次放下左边的筷子和右边的筷子
        forks[id].release();
        forks[(id + 4) % 5].release();
    }

    private void thinking() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
    }


    public static void main(String[] args) {
        for (int i = 0; i < number; i++) {
            new Thread(new 哲学家吃饭其中一个人改变拿筷子顺序(i)).start();
        }
    }
}
