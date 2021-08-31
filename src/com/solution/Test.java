package com.solution;


import java.io.IOException;

/**
 * @author lxq
 * @date 2021年07月28日 17:40
 */
public class Test {

    static class parent{
        public static int a = 1;
        static {
            a = 2;
        }
    }

    static class child extends parent{
        public static int B = a;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(child.B);
    }



}
