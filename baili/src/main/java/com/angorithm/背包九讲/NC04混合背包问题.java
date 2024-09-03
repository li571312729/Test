package com.angorithm.背包九讲;

public class NC04混合背包问题 {

    public static void main(String[] args) {
        System.out.println(optimizationPackages(
                11, 4, new int[]{3, 4, 2, 5},
                new int[]{4, 5, 3, 8}, new int[]{3, 0, 4, 1}));
    }

    /**
     * 混合背包问题
     * 问题
     * 如果将前面三个背包混合起来，也就是说，有的物品只可以取一次（01背包），
     * 有的物品可以取无限次（完全背包），有的物品可以取的次数有一个上限（多重背包），
     * 应该怎么求解呢？
     *
     * 01背包与完全背包的混合
     * 考虑到在01背包和完全背包中给出的伪代码只有一处不同，
     * 故如果只有两类物品：一类物品只能取一次，另一类物品可以取无限次，
     * 那么只需在对每个物品应用转移方程时，根据物品的类别选用顺序或逆序的循环即可，
     * 复杂度是O ( V N ) O(VN)O(VN)。
     *
     *
     * @author lxq
     * @date 2021/8/18 13:52
     */
    public static int optimizationPackages(int V, int n, int[] w, int[] v, int[] p){
        // 1, dp[v] 表示容量为v的背包可以获得的最大价值
        int[] dp = new int[V + 1];

        // 3, 初始化 dp[0][x] = 0

        for (int i = 0; i <= n - 1 ; i ++){
            if(p[i] == 0){
                for (int j = w[i]; j <= V ; j++){
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }
            }else {
                int num = Math.min(p[i], V/w[i]);

                for (int k = 1;  num > 0 ; k++) {
                    if(k > num){
                        k = num;
                    }
                    num -= k;
                    for (int j = V; j >= k * w[i] ; j--){
                        // dp[j]表示在上一次拿完的基础上我再拿k个之后的最大值
                        dp[j] = Math.max(dp[j], dp[j - k * w[i]] + k * v[i]);
                    }
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
