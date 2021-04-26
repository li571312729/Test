package com.baili.test;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class test6 {

    public static ThreadLocal<Integer> a = new ThreadLocal<>();

    @SneakyThrows
    public static void main(String[] args) {
         CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            a.set(10);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }
            System.out.println("c : " + a.get());
        });

        CompletableFuture<Void> d = CompletableFuture.runAsync(() -> {
            System.out.println("d start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
            System.out.println("d : " + a.get());
        });
        c.get();
        d.get();
    }

}
