package com.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * @author lxq
 * @date 2021年08月17日 14:15
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(1111111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(thread.isDaemon());
        System.out.println(22222);
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
