package com.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author lxq
 * @date 2021年08月17日 14:15
 */
public class Test {
    public static void main(String[] args) {
        int num = 12;
        int[] a = new int[]{5, 2, 3};
        int[] dp = new int[a.length];
        dp[0] = 0;

        ThreadLocal<DateFormat> d = new ThreadLocal<DateFormat>(){
            @Override
            protected SimpleDateFormat initialValue() {
                System.out.println(111111);
                return new SimpleDateFormat("");
            }
        };

        Thread threadA = new Thread(() -> {
            SimpleDateFormat dateFormat = (SimpleDateFormat) d.get();
            System.out.println(dateFormat);
        });
        Thread threadB = new Thread(() -> {
            SimpleDateFormat dateFormat = (SimpleDateFormat) d.get();
            System.out.println(dateFormat);
        });

        threadA.start();
        threadB.start();
    }

    public static boolean contains(int[] a, int num){
        for (int i : a){
            if (i == num) {
                return true;
            }
        }
        return false;
    }
}
