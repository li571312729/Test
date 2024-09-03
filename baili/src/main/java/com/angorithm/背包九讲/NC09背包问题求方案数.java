package com.angorithm.背包九讲;

import java.util.Arrays;

/**
 * @author lxq
 * @date 2021年08月30日 11:07
 */
public class NC09背包问题求方案数 {

    public static void main(String[] args) {
        System.out.println(optimizationPackages(11, 5, new int[]{3, 4, 2, 5, 5},new int[]{4, 4, 3, 7, 7}));
    }

    /**
     * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
     * 第 i 件物品的体积是 vi，价值是 wi。
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出 最优选法的方案数。注意答案可能很大，请输出答案模 10^9+7 的结果。
     *
     */
    public static int optimizationPackages(int V, int n, int[] w, int[] v){
        // 1, dp[v] 表示恰好容量为v时的最大价值（这里跟前面不一样在于，这里体积必须用完，之前是可以不用完）
        int[] dp = new int[V + 1];
        // 体积恰好为v时的方案数
        int[] g = new int[V + 1];
        g[0] = 1;

        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= n - 1 ; i ++){
            for (int j = V; j >= w[i] ; j--){
                int t = Math.max(dp[j], dp[j - w[i]] + v[i]);
                int s = 0;

                // 这里这样写是因为存在重量相同，价值也相同的情况，
                // 也就是dp[j]和dp[j - w[i]] + v[i]相同，这时候需要累加s（方案数）
                if(t == dp[j]){
                    s += g[j];
                }

                if(t == dp[j - w[i]] + v[i]){
                    s += g[j - w[i]];
                }

                // 这里是判断可要可不要，负无穷理论上不会超过0，当然加上判断则只需要恰好是该容量的情况
//                if(t >= 0){
                    dp[j] = t;
//                }
                g[j] = s;
            }
            System.out.println(Arrays.toString(dp));
            System.out.println(Arrays.toString(g));
            System.out.println();
        }
        return g[V];
    }

}
