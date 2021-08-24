package com.solution.背包九讲;

import java.util.Arrays;

/**
 * @author lxq
 * @date 2021年08月24日 9:25
 */
public class NC05二维费用背包问题 {

    public static void main(String[] args) {
        System.out.println(optimizationPackages(
                11, 10, 4, new int[]{3, 4, 2, 5},
                new int[]{4, 5, 3, 6}, new int[]{5, 6, 4, 7}));
    }

    /**
     * 二维费用的背包问题是指：对于每件物品，具有两种不同的费用；
     * 选择这件物品必须同时付出这两种代价；对于每种代价都有一个可付出的最大值（背包容量）。
     * 问怎样选择物品可以得到最大的价值。设这两种代价分别为代价1 和代价2 ，
     * 第i 件物品所需的两种代价分别为w [ i ] 和g [ i ] 。
     * 两种代价可付出的最大值（两种背包容量）分别为V 和 T。物品的价值为v [ i ] 。
     *
     * @author lxq
     * @date 2021/8/18 13:52
     */
    public static int optimizationPackages(int V, int T, int n, int[] w, int[] g, int[] v){
        // 1, dp[i][j][k] 表示取前i件物品时在代价为j和k的情况下的最大价值
        int[][] dp = new int[V + 1][T + 1];

        // 2. dp[i][j][k] = max{dp[i - 1][j][k], dp[i - 1][j - w[i]]][j - g[i]] + v[i]}
        // dp[j][k] = max{dp[j][k], dp[j - w[i]][j - g[i]] + v[i]}

        for (int i = 0; i <= n - 1 ; i ++){
            for (int j = V; j >= w[i] ; j--){
                for (int k = T; k >= g[i] ; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - w[i]][k - g[i]] + v[i]);
                }
            }
            String s = Arrays.deepToString(dp);
            System.out.println(s);
        }
        return dp[V][T];
    }

}
