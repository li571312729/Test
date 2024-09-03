package com.baili.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test13 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        ExecutorService executorService = Executors.newCachedThreadPool();
        long l = System.currentTimeMillis();

        for (int i = 0; i < 50000000; i++) {
            MyTask myTask = new MyTask(i);
            executorService.execute(myTask);
            long completedTaskCount = ((ThreadPoolExecutor) executorService).getCompletedTaskCount();
            if(completedTaskCount > 200000){
                System.out.println("工作完成共耗时:" + (System.currentTimeMillis() - l));
                executorService.shutdownNow();
            }
            System.out.println("当前线程数:" + ((ThreadPoolExecutor)executorService).getPoolSize()
            + "  ,等待队列中任务数:" + ((ThreadPoolExecutor)executorService).getQueue().size()
                    + "  ,完成任务数量:" + completedTaskCount);
        }

    }
}


class MyTask implements Runnable {
    private int taskNum;
    public MyTask(int num) {
        this.taskNum = num;
    }
    public static AtomicInteger a = new AtomicInteger();
    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.incrementAndGet();
        System.out.println("task " + taskNum + "执行完毕");
    }
}
