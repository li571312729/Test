package com.angorithm.thread;

/**
 * @author lxq
 * @date 2021年10月13日 13:35
 */
public class 双线程通信 {

    /**
     * 两个线程交替打印数字到100
     * @author lxq
     * @date 2021/10/13 13:36
     * @param args
     */
    public static void main(String[] args) {
        PrintThread printThread = new PrintThread();
        Thread threadA = new Thread(printThread, "threadA");
        Thread threadB = new Thread(printThread, "threadB");

        threadA.start();
        threadB.start();
    }


}

class PrintThread extends Thread{

    private int count = 0;

    @Override
    public void run() {
        synchronized (this){
            while (count < 100) {
                try {
                    notify();
                    System.out.println(Thread.currentThread().getName() + ":" + ++count);
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            notifyAll();
        }
    }

}