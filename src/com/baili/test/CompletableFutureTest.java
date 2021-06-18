package com.baili.test;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8为我们提供了CompletableFuture类来实现异步处理
 *
 * @author Administrator
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureTest test = new CompletableFutureTest();
        // runAsync() 异步无参返回
        // test.runAsync();

        // supplyAsync() 异步有参返回
         // test.supplyAsync();

         // allOf() 多个异步处理(针对有参返回)
         //test.allOf();

        // anyOf() 多个异步随机处理(针对有参返回)
        //test.anyOf();

        test.method();
    }

    /**
     * 多个异步随机处理(针对有参返回)
     */
    @SneakyThrows
    private void anyOf() {
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(20);
                return "hello";
            } catch (Exception e) {
                e.printStackTrace();
                return "none~";
            }
        });
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> "youth");
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> "!");

        CompletableFuture<Object> any = CompletableFuture.anyOf(a, b, c);
        String result = (String) any.get();
        System.out.println(result);
    }

    /**
     * 多个异步处理(针对有参返回)
     */
    @SneakyThrows
    private void allOf() {
        CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> "world");
        CompletableFuture<String> c3 = CompletableFuture.supplyAsync(() -> "!");
        CompletableFuture<Void> all = CompletableFuture.allOf(c1, c2, c3);
        all.get();

        String result = Stream.of(c1, c2, c3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining("  "));
        System.out.println(result);
    }

    /**
     * 异步有参返回
     */
    @SneakyThrows
    private void supplyAsync() {
        CompletableFuture<Integer> c1 = CompletableFuture.supplyAsync(() -> {
            int result = 0;
            for (int i = 0; i < 10000; i++) {
                result += i;
            }
            return result;
        });
        System.out.println(c1.get(10, TimeUnit.SECONDS));
    }

    /**
     * 异步无参返回
     */
    @SneakyThrows
    private void runAsync() {
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println("none return aunAsync");
        });
        // get同步方法等待结果
        System.out.println(c1.get());
    }

    private void method() throws ExecutionException, InterruptedException {
        StringBuilder builder = new StringBuilder();
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "f1";
        });

        f1.whenCompleteAsync(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                builder.append(s);
            }
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "f2";
        });

        f2.whenCompleteAsync(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                builder.append(s);
            }
        });

        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2);

        all.get();

        System.out.println(builder.toString());
    }

}


