package com.concurrent.thread;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 当一个线程调用了一个共享变量的wait（）方法时，该调用线程会被阻塞挂起，并且释放该共享变量上的锁
 * @author lxq
 * @date 2021年07月30日 11:48
 */
public class WaitTest {

   static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
   static Integer MAX_SIZE = 10;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true){
                // 生产线程
                synchronized (queue) {

                    while (queue.size() == MAX_SIZE) {
                        // 挂起当前线程，并释放同步锁上获取的queue的锁，让消费者可以获取该锁，然后能获取队列里的元素
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // 队列有空闲则生成元素加入队列，并通知其他持有该锁的消费者
                    queue.add("hello" + queue.size());
                    System.out.println(LocalDateTime.now() + "->生产元素:hello" + (queue.size() - 1));
                    queue.notifyAll();
                }
            }
        });

        ThreadTest threadTest = new ThreadTest();
        thread.start();
        threadTest.start();


    }


    static class ThreadTest extends Thread{

        @SneakyThrows
        @Override
        public void run() {
            while (true){
                // 消费者线程
                synchronized (queue){
                    while (queue.size() == 0){
                        // 当队列为空时，挂起当前线程，并释放同步锁上获取的queue的锁，让生产者可以获取该锁，然后将生产元素放入队列
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // 消费元素，并通知生产者线程
                    String take = queue.take();
                    System.out.println(LocalDateTime.now() + "->消费元素:" + take);
                    queue.notifyAll();
                }
            }
        }
    }

}
