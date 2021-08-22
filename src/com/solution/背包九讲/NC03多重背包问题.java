package com.solution.背包九讲;

public class NC03多重背包问题 {

    public static void main(String[] args) {
        System.out.println(optimizationPackages(
                11, 4, new int[]{3, 4, 2,5},
                new int[]{2, 4, 3, 7}, new int[]{3, 2, 4, 1}));
    }

    /**
     * 有N 件物品和一个容量为V的背包。
     * 第i件物品的体积是w [i]，
     * 价值是v[i]，数量是p[i]
     * 求将哪些物品装入背包可使价值总和最大。
     *
     * @author lxq
     * @date 2021/8/18 13:52
     */
    public static int optimizationPackages(int V, int n, int[] w, int[] v, int[] p){
        // 1, dp[v] 表示容量为v的背包可以获得的最大价值
        int[] dp = new int[V + 1];

        // 2, f[i][j]=max(f[i−1][j−k∗w[i]]+k∗v[i])∣0<=k<=p[i]

        // 3, 初始化 dp[0][x] = 0

        // 获取每个物品最大能选多少件，取决于物品有多少件，以及背包最多选多少件
        int num;
        for (int i = 0; i <= n - 1 ; i ++){
            num = Math.min(p[i], V/w[i]);
            System.out.println("第" + (i + 1) +  "件物品，该物品能拿的数量： " + num);
            for (int k = 1;  num > 0 ; k++) {
                if (k > num) {
                    // 这里防止当前这次拿的超过剩余的数量，如果超过那就只能拿剩余这些
                    k = num;
                }
                // 这样是保证依次拿第一件物品时，每次递增拿的数量和之前的相加不超过总共能拿的数量
                num = num - k;
                for (int j = V; j >= k * w[i] ; j--){
                    // dp[j]表示在上一次拿完的基础上我再拿k个之后的最大值
                    dp[j] = Math.max(dp[j], dp[j - k * w[i]] + k * v[i]);
                }
                System.out.print("第" + (i + 1) +  "件物品拿" + k + "个：");
                print1(dp);
            }
            System.out.println();
        }
        return dp[V];
    }

    // 这个方法跟上面的区别是我拿的时候一件一件拿，上面是 递增拿 第一次拿1个，第二次2个，第三次3个，，一直到最后剩余的
    public static int optimizationPackages1(int V, int n, int[] w, int[] v, int[] p){
        // 1, dp[v] 表示容量为v的背包可以获得的最大价值
        int[] dp = new int[V + 1];

        // 2, f[i][j]=max(f[i−1][j−k∗w[i]]+k∗v[i])∣0<=k<=p[i]

        // 3, 初始化 dp[0][x] = 0

        // 获取每个物品最大能选多少件，取决于物品有多少件，以及背包最多选多少件
        int num;
        for (int i = 0; i <= n - 1 ; i ++){
            num = Math.min(p[i], V/w[i]);
            for (int k = 1;  k <= num ; k++) {
                for (int j = V; j >= w[i] ; j--){
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }
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
