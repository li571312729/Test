package com.solution;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author lxq
 * @date 2021年06月02日 16:29
 */
public class 选择物品 {

    //TODO 未完成，，，有bug
    public static void main(String[] args) {
        test(6, 3);
    }

    public static void test(int n, int m){
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i + 1;
        }
        test(data, m);
    }

    /**
     * 123
     * 124
     * 143
     * 243
     *
     * n个物品，选择其中m个 输出所有选择
     * @author lxq
     * @date 2021/6/2 16:30
     * @param n
     * @param m 
     */
    public static void test(int[] n, int m){
        if(n.length < m){
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < m; i++) {
            buffer.append(n[i]);
        }
        int start = Integer.parseInt(buffer.toString());
        System.out.println(start);

        for (int i = m - 1, t = 0; i > 0; i --, t++) {
            int temp = start + 10 * t;
            for (int j = i + (t + 1); j < n.length; j++) {
                if(sort(++temp)){
                    System.out.println(temp);
                }
            }
        }
        test(Arrays.copyOfRange(n, 1, n.length - 1), m);
    }

    private static boolean sort(int temp) {
        char[] chars = String.valueOf(temp).toCharArray();
        for (int i = chars.length - 1; i > 0 ; i--) {
            if(chars[i] <= chars[i - 1]){
                return false;
            }
        }
        return true;
    }


}

