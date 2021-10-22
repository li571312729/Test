package com.solution.thread;

import com.iterator.ArrayList_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxq
 * @date 2021年10月13日 13:35
 */
public class N个线程通信 {

    private static final int number  = 4;

    /**
     * N个线程交替打印数字到100
     * @author lxq
     * @date 2021/10/13 13:36
     * @param args
     */
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(new PrintThreadTest(i, number, "thread" + i));
        }
        list.forEach(o -> o.start());
    }

}

class PrintThreadTest extends Thread{

    private static int count = 0;
    private int num;
    private int total;
    // 线程间使用的同步对象
    private static Object object = new Object();

    public PrintThreadTest(int num, int total, String threadName) {
        super(threadName);
        this.num = num;
        this.total = total;
    }

    @Override
    public void run() {
        synchronized (object){
            while (count <= 100) {
                try {
                    object.notifyAll();
                    if(count % total == num){
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            object.notifyAll();
        }
    }

}