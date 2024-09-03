package com.angorithm;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) throws IOException, InterruptedException {
        Test e1 = new Test();
        Test e2 = new Test();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> e1.after());
        executorService.execute(() -> e2.before());
        executorService.shutdown();
    }

    public void before() {

        lock.lock();
        try {
            System.out.println("before");
            condition.signalAll();
            System.out.println(11111);
        } finally {
            lock.unlock();
}
    }

    public void after() {
        lock.lock();
        try {
            condition.await();
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
