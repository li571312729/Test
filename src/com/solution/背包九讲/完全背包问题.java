package com.solution.背包九讲;

/**
 * @author lxq
 * @date 2021年08月19日 15:08
 */
public class 完全背包问题 {

    public static void main(String[] args) {
        System.out.println(optimizationPackages(11, 4, new int[][]{{6, 4}, {7, 8}, {4, 3}, {2, 3}}));
    }

    /**
     * 有N 件物品和一个容量为V的背包。
     * 每件物品的数量是无限的
     * 第i件物品的体积是w [i]，
     * 价值是v[i]，求将哪些物品装入背包可使价值总和最大。
     *
     * 第一个物品的体积为1，价值为3，第二个物品的体积为10，价值为4
     *
     * @author lxq
     * @date 2021/8/18 13:52
     */
    public static int optimizationPackages(int V, int n, int [][] thing){
        // 1, dp[v] 表示容量为v的背包可以获得的最大价值
        int[] dp = new int[V + 1];

        // 2, dp[j] = Math.max([j], [j - w[i]] + v[i])

        // 3, 初始化 dp[0][x] = 0

        // 4 这里跟0,1背包不一样的地方只有遍历体积时是正序排列，这是因为这里物品数量无限，
        // 则先计算前面的物品，后面可以有机会再次添加该物品
        for (int i = 1; i <= n ; i ++){
            for (int j = thing[i - 1][0]; j <= V ; j++){
                dp[j] = Math.max(dp[j], dp[j - thing[i - 1][0]] + thing[i - 1][1]);
            }
            print1(dp);
        }
        return dp[V];
    }

    public static void print1(int[] dp){
        for (int ints : dp) {
            System.out.print(ints + "   ");

        }
        System.out.println();
    }

}
