package com.solution.背包九讲;

import java.util.Arrays;

public class NC08泛化物品问题 {

    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 0, 0, 5, 7, 0, 15, 0, 20, 0, 20, 22, 0};
        int[] b = new int[]{0, 0, 3, 0, 4, 8, 9, 12, 0, 0, 0, 0, 0, 0};
        int[] c = new int[]{0, 2, 4, 0, 6, 7, 0, 0, 0, 0, 15, 22, 25, 30};
        Object[] arr = new Object[]{a, b, c};
        System.out.println(optimizationPackages(3, 15, a, b, c, arr));
    }

    /**
     *
     * 考虑这样一种物品，它并没有固定的费用和价值，
     * 而是它的价值随着你分配给它的费用而变化。这就是泛化物品的概念。
     * 在背包容量为V的背包问题中，泛化物品是一个定义域为0..V中的整数的函数h，当分配给它的费用为v时，能得到的价值就是h(v)。
     * 这个定义有一点点抽象，另一种理解是一个泛化物品就是一个数组h[0..V]，给它费用v，可得到价值h[V]。
     *
     * 这里泛化物品我们简单认为是01背包问题(即每个物品只能挑选一次也就是选择一种费用对应的价值)，
     * 当然也可以是完全，多重，或者混合背包等等、。、、
     * @author lxq
     * @date 2021/8/18 13:52
     *
     * n 物品数量
     * v 背包容量
     * a[] a物品重量/价值集合 a[i]表示重量为i时的价值，如果等于0表示该物品不存在重量为i的情况
     * b[] b物品重量/价值集合 b[i]表示重量为i时的价值，如果等于0表示该物品不存在重量为i的情况
     * c[] c物品重量/价值集合 c[i]表示重量为i时的价值，如果等于0表示该物品不存在重量为i的情况
     */
    public static int optimizationPackages(int n, int v, int[] a, int[] b, int[] c, Object[] arr){
        // dp[i] 表示背包容量为i时的最大价值
        int[] dp = new int[v + 1];

        // dp[v] =max(a(k) + b(m) + c(v - k - m)∣0<=k<=v | 0<=m<=v | 0<= m + k <=v)

        // 先从第一个物品开始，然后从容量倒序，每一个容量都需要遍历当前物品所有容量（也是倒序
        // ）
        for (int i = 0; i < n ; i ++){
            for (int j = v; j >= 0; j--){
                for (int k = Math.min(j, ((int[])arr[i]).length - 1); k >= 0; k--) {
                    dp[j] = Math.max(dp[j], dp[j - k] + ((int[])arr[i])[k]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
            return dp[v];
    }

}
