package com.solution;

/**
 * @author lxq
 * @date 2021年08月18日 13:51
 */
public class NC01背包问题 {

    public static void main(String[] args) {
        System.out.println(optimizationPackages(11, 3, new int[][]{{6, 4}, {7, 8}, {4, 3}}));
    }

    /**
     * 有N 件物品和一个容量为V的背包。第i件物品的体积是w [ i ]，
     * 价值是v[i]，求将哪些物品装入背包可使价值总和最大。
     *
     * 第一个物品的体积为1，价值为3，第二个物品的体积为10，价值为4
     * @author lxq
     * @date 2021/8/18 13:52
     */
    public static int packages(int V, int n, int [][] thing){
        // 1, dp[n][v] 表示前n件物品放入一个容量为v的背包可以获得的最大价值
        int[][] dp = new int[n + 1][V + 1];

        // 2, dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i])

        // 3, 初始化 dp[0][x] = 0

        // 4 遍历，先考虑上面讲的基本思路如何实现，肯定是有一个主循环i = 1... N，
        // 每次算出来二维数组f[i][0...V]的所有值。 先遍历物品或者背包容量都可以s
        for (int i = 1; i <= n ; i ++){
            for (int j = 1; j <= V; j++){
                dp[i][j] = dp[i - 1][j];

                if(j >=  thing[i - 1][0]){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - thing[i - 1][0]] + thing[i - 1][1]);
                }
            }
            print(dp);
        }

        return dp[n][V];
    }

    /**
     * 优化
     * @author lxq
     * @date 2021/8/18 14:32
     * @param V
     * @param n
     * @param thing
     * @return int
     */
    public static int optimizationPackages(int V, int n, int [][] thing){
        // 1, dp[v] 表示容量为v的背包可以获得的最大价值
        int[] dp = new int[V + 1];

        // 2, dp[j] = Math.max([j], [j - w[i]] + v[i])

        // 3, 初始化 dp[0][x] = 0

        // 4 将二维空间降为一维空间，倒序遍历防止物品被计算多次，但是完全背包就是用的正序（因为数量无限） 可以自己用dp【】推算
        // 而且这里只能先遍历物品，再遍历背包容量
        for (int i = 1; i <= n ; i ++){
            for (int j = V; j >=  thing[i - 1][0]; j--){
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

    public static void print(int[][] dp){
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + "   ");
            }
            System.out.println();
        }

    }
}
