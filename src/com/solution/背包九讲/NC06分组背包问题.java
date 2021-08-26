package com.solution.背包九讲;

import java.util.Arrays;

public class NC06分组背包问题 {


    public static void main(String[] args) {
        System.out.println(optimizationPackages(
                11, new int[]{3, 4, 2, 5},
                new int[]{4, 5, 3, 6}, new int[][]{{0, 1}, {2 , 3}}));
    }

    /**
     * 有N 件物品和一个容量为V 的背包。第i件物品的费用是w [ i ] ,
     * 价值是v [ i ] 。这些物品被划分为若干组，每组中的物品互相冲突，
     * 最多选一件。求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，
     * 且价值总和最大。分组为g
     *
     * @author lxq
     * @date 2021/8/18 13:52
     */
    public static int optimizationPackages(int V, int[] w, int[] v, int[][] g){
        // 1, dp[j] 表示容量为j时情况下的最大价值
        int[] dp = new int[V + 1];

        // 2. dp[j] = max{dp[j], dp[j - w[i]] + v[i]}

        // 先以组进行循环，然后固定容量 遍历组内每一个 保证每一组选一个，然后切换组
        for (int i = 0; i < g.length ; i ++){
            for (int j = V; j >= 0; j--){
                for (int k = 0; k < g[i].length; k++) {
                    if(j >= w[g[i][k]]){
                        dp[j] = Math.max(dp[j], dp[j - w[g[i][k]]] + v[g[i][k]]);
                     }
                }
            }
            String s = Arrays.toString(dp);
            System.out.println(s);
        }
        return dp[V];
    }
}
