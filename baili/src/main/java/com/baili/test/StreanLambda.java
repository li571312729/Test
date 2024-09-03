package com.baili.test;

import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class StreanLambda {

    public static void main(String[] args) {
        IntStream is = IntStream.builder().add(100).add(5).add(100).add(-15).build();
        PrintStream out = System.out;
        is.sorted().distinct().limit(2).map(Item -> Item).forEach( out :: println);
        
        Consumer<String> fun = out :: printf;
        fun.accept("123");
        
    }

}
