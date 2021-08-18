package com.concurrent;

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
