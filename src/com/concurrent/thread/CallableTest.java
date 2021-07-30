package com.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用callable配合future可以获得线程返回值
 * futureTask
 * @author lxq
 * @date 2021年07月30日 10:59
 */
public class CallableTest implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "call";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableTest());
        new Thread(futureTask).start();
        String s = futureTask.get();
        System.out.println(s);

    }

}
