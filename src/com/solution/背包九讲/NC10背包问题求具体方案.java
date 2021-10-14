package com.solution.背包九讲;

import java.util.Arrays;

/**
 * @author lxq
 * @date 2021年08月31日 9:50
 */
public class NC10背包问题求具体方案 {

    public static void main(String[] args) {
        System.out.println(optimizationPackages(11, 4, new int[]{3, 4, 2, 5},new int[]{4, 4, 3, 7}));
    }

    /**
     * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
     *
     * 第 i 件物品的体积是 vi，价值是 wi。
     *
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     *
     * 输出 字典序最小的方案。这里的字典序是指：所选物品的编号所构成的序列。物品的编号范围是 1…N。
     *
     */
    public static String optimizationPackages(int V, int n, int[] w, int[] v){
        // 1, dp[n][v] 表示前n件物品放入一个容量为v的背包可以获得的最大价值
        int[][] dp = new int[n + 2][V + 2];
        StringBuffer data = new StringBuffer();

        for (int i = n; i >= 1 ; i --){
            for (int j = 1; j <= V; j++){
                dp[i][j] = dp[i + 1][j];
                if(j >=  w[i - 1]){
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - w[i - 1]] + v[i - 1]);
                }
            }
            System.out.println(Arrays.deepToString(dp));
        }

        int tmpV = V;
        for (int i = 1; i <= n; i++){
            // 如果剩余体积大于当前物品重量，并且的当前物品对应体积值等于下一个物品要选他的结果，那当前物品被选择了
            if (tmpV >=  w[i - 1] && dp[i][tmpV] == dp[i + 1][tmpV - w[i - 1]] + v[i - 1]) {
                data.append(i);
                tmpV -= w[i - 1];
            }
        }
        return data.toString();
    }

}
