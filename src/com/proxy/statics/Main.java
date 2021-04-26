package com.proxy.statics;

public class Main {

    public static void main(String[] args) {
        new TimeProxy(
                new Logproxy(
                        new Car("car01", "红旗")))
        .run();
        
        System.out.println();
        new Logproxy(
                new TimeProxy(
                        new Bicycle()))
        .run();
    }

}
