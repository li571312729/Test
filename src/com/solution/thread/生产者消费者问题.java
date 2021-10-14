package com.solution.thread;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lxq
 * @date 2021年10月13日 15:08
 */
public class 生产者消费者问题 {

    private static Queue<Object> queue = new ArrayBlockingQueue<>(10);
    private static Lock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();

    public static void main(String[] args) {
        Thread product = new Thread(() -> {
            lock.lock();
            try {
                while (true){
                    if(queue.size() < 10){
                        queue.add(new Object());
                        System.out.println(Thread.currentThread().getName() + "生产了一个对象。现在仓库大小：" + queue.size());
                        notEmpty.signalAll();
                    }else {
                        System.out.println("仓库大小：" + queue.size() + "  等待消费者消费。");
                        notFull.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "product");

        Thread consumer = new Thread(() -> {
            lock.lock();
            try {
                while (true){
                    if(queue.isEmpty()){
                        System.out.println("仓库大小：" + queue.size() + "  等待生产者生产。");
                        notEmpty.await();
                    }else {
                        queue.poll();
                        System.out.println(Thread.currentThread().getName() + "消费了一个对象。现在仓库大小：" + queue.size());
                        notFull.signalAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "consumer");

        consumer.start();
        product.start();
    }

}
