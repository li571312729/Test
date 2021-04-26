package com.baili.test;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
    
    public static void main(String[] args) {
        for(int i = 0; i < 10000; i++){
            queue.add(i + "");
        }
        //functionSize();
        functionEmpty();
    }

    @SuppressWarnings("unused")
    private static void functionEmpty() {
        long t1 = System.currentTimeMillis();
        StringBuilder sBuilder = new StringBuilder();
        while(!queue.isEmpty()){
            sBuilder.append(queue.poll());
        }
        System.out.println(sBuilder);
        System.out.println(System.currentTimeMillis() - t1);
        
    }
    
    @SuppressWarnings("unused")
    private static void functionSize() {
        long t1 = System.currentTimeMillis();
        StringBuilder sBuilder = new StringBuilder();
        while(queue.size() > 0){
            sBuilder.append(queue.poll());
        }
        System.out.println(sBuilder);
        System.out.println(System.currentTimeMillis() - t1);
    }

}
