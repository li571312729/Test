package com.baili.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Gp<T, A> {
    
    private T name;
    
    private A sex;
    
    public String bbb;
    
    public Gp(){};
    
    public Gp(T t, A a){
        this.name = t;
        this.sex = a;
    };
    
    public static <T extends Object> void find(T a){
        if(a instanceof Integer )
            System.out.println(a);
    }
    

    public static void main(String[] args) throws InterruptedException {
//        Gp gp = new Gp<String, Integer>("小工", 1);
//        System.out.println(gp.getName());
//
//        find(1122);
//        find(1122.4d);
//
//        Gp gp2 = new Gp();
//        System.out.println(gp2.getName());
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    int i = 0;
                    i ++;
                    if(i % 3 == 0){
                        throw new IllegalArgumentException("11");
                    }
                    System.out.println(Thread.currentThread().getName() + "执行" + "No。");

                }
            });
        TimeUnit.SECONDS.sleep(10);


            singleThreadExecutor.shutdown();
    }
    
    
    

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public A getSex() {
        return sex;
    }

    public void setSex(A sex) {
        this.sex = sex;
    }

}
