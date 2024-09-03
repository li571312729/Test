package com.concurrent.lock;

/**
 * @author lxq
 * @date 2021年08月03日 16:17
 */
public class 缓存行 {


    public static void main(String[] args) {
        int[][] a = new int[1024][1024];
        int[][] b = new int[1024][1024];

        long l = System.currentTimeMillis();
        /**
         * cpu加载内存数据时按缓存行加载，因此连续修改数组行时减少访问主内存的次数
         */
        for (int i = 0; i < 1024 ; i++) {
            for (int j = 0; j < 1024; j++){
                a[i][j] = i * 2 + j;
            }
        }
        long l1 = System.currentTimeMillis();

        /**
         * cpu加载内存数据时按缓存行加载，因此连续修改数列行时相当于跳跃式访问数组元素，破坏了程序访问的局部性（一次加载局部内存的做法）
         */
        for (int i = 0; i < 1024 ; i++) {
            for (int j = 0; j < 1024; j++){
                a[j][i] = i * 2 + j;
            }
        }
        long l2 = System.currentTimeMillis();

        System.out.println(l1 - l);
        System.out.println(l2 - l1);

    }
}
