package com.thread;

import com.thread.threadPool.ConcurrentThreadPool;
import com.thread.threadPool.IConcurrentThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @date 2021年07月06日 14:48
 */
public class Test {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<TaskA> aList = new ArrayList<>(10);
        List<TaskB> bList = new ArrayList<>(10);

        for (int i = 0; i < 10; i++){
            List<Integer> data = new ArrayList<>(10);
            for (int j = 0; j < 10; j++){
                data.add((int) (Math.random() * 10 + 1));
            }
            TaskA a = new TaskA(data);
            aList.add(a);

            TaskB taskB = new TaskB("hello" + (int) (Math.random() * 10 + 1));
            bList.add(taskB);
        }

        IConcurrentThreadPool threadPool = new ConcurrentThreadPool();
        // 初始化线程池配置
        threadPool.initConcurrentThreadPool();

        // 执行多个任务
        List<String> strings = threadPool.invokeAll(aList);

//        List<Integer> integers = threadPool.invokeAll(bList);

    }

}
