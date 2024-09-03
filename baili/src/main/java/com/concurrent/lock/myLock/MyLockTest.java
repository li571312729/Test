package com.concurrent.lock.myLock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;

/**
 * @author lxq
 * @date 2021年08月13日 11:00
 */
public class MyLockTest {

    final static NoReentrantLock lock = new NoReentrantLock();
    final static Condition notEmpty = lock.newCondition();
    final static Condition notFull = lock.newCondition();
    final static ThreadLocalRandom random = ThreadLocalRandom.current();
    final static LongAdder sum = new LongAdder();

    final static int queneSize = 10;
    final static Queue<String> queue = new LinkedBlockingDeque<>();

    public static void main(String[] args) throws InterruptedException {
        Thread product = new Thread(() -> {

            lock.lock();
            try {
                for (;;){
                    // 队列满了则等待
                    while (queue.size() == queneSize){
                        notEmpty.await();
                    }

                    String ele = "ele" + random.nextInt(99999);
                    queue.add(ele);
                    sum.increment();
                    System.out.println("大小-> " + queue.size() + ",   共生产-> " + sum.longValue() + "，  本次生产：" + ele);
                    notFull.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread consumer = new Thread(() -> {

            lock.lock();
            try {
                for (;;){
                    // 队列满了则等待
                    while (queue.size() == 0){
                        notFull.await();
                    }

                    String ele = queue.poll();
                    System.out.println("大小-> " + queue.size() + "， 消费:" + ele);

                    notEmpty.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        product.start();
        consumer.start();

        product.join();
        consumer.join();
        System.out.println("exit");
    }



}
