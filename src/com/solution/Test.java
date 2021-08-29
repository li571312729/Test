package com.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lxq
 * @date 2021年07月28日 17:40
 */
public class Test {

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String s = b.readLine();
        System.out.println(s);
        new Thread(() -> {
            while (true){}
        }, "Abcde").start();

    }

}
