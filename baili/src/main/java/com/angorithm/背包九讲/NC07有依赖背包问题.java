package com.angorithm.背包九讲;

import java.util.Arrays;

/**
 * @author lxq
 * @date 2021年08月27日 10:50
 */
public class NC07有依赖背包问题 {

    public static void main(String[] args) {

        System.out.println(optimizationPackages(4, 5, new int[]{3, 4, 2, 5},
                new int[]{4, 5, 3, 6}, new int[][]{{1,2},{3}, {}, {}}));
    }

    /**
     *
     * 这种背包问题的物品间存在某种“依赖”的关系。也就是说，i
     * 依赖于j，表示若选物品i ，则必须选物品j 。
     * 将不依赖于别的物品的物品称为“主件”，依赖于某主件的物品称为“附件”
     * 主件的附件仍然可以具有自己的附件集合，
     * 限制只是每个物品最多只依赖于一个物品（只有一个主件）且不出现循环依赖
     *
     * @author lxq
     * @date 2021/8/18 13:52
     *
     * n 物品数量
     * V 背包容量
     * w[] 物品重量集合
     * v[] 物品价值集合
     * p[] 物品依赖集合
     */
    public static int optimizationPackages(int n, int V, int[] w, int[] v, int[][] p){
        // dp[i][j] 表示选择i物品作为子树，容量不超过j时的最大价值
        int[][] dp = new int[n + 1][V + 1];

        dfs(0, dp, V, w, v, p);

        return dp[0][V];
    }

    private static void dfs(int x, int[][] dp, int V, int[] w, int[] v, int[][] p) {
        // //点x必须选，所以初始化 dp[x][w[x] ~ V]= v[x]
        for (int i = w[x]; i <= V ; i++) {
            dp[x][i] = v[x];
        }

        for (int i = 0; i < p[x].length; i++) {
            int y = p[x][i];
            dfs(y, dp, V, w, v, p);
            // //j的范围为w[x]~V, 小于w[x]无法选择以x为子树的物品
            for (int j = V; j >= w[x]; j--){
                //分给子树y的空间不能大于j-w[x],不然都无法选根物品x
                for (int k = 0; k <= j - w[x]; k++){
                    dp[x][j] = Math.max(dp[x][j], dp[x][j - k] + dp[y][k]);
                }
            }
            System.out.println(Arrays.deepToString(dp));
            System.out.println();
        }
    }

}
