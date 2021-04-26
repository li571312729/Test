package com.baili.test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadA {
    private static AtomicInteger atom = new AtomicInteger();
    private static List<Byte[]> list = new ArrayList<>();
    private static ReferenceQueue<HelloWorld> queue = new ReferenceQueue<>();
    private static PhantomReference<HelloWorld> p = new PhantomReference<HelloWorld>(new HelloWorld(), queue );
    
    public static void main(String[] args) {  
        Thread AA = new Thread(() -> {
            while(true){
                try {
                    list.add(new Byte[1024*1024]);
                    System.out.println(atom.incrementAndGet());
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                }
            }
        }, "a");
        
        AA.start();
        
        while(true){
            Reference<?> poll = queue.poll();
            if(poll != null){
                System.err.println("虚引用对象被JVM回收----" + poll);
                AA.interrupt();
            }
        }
        
    }
}

