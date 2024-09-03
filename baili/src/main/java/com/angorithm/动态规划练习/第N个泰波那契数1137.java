package com.angorithm.动态规划练习;

/**
 * @author xiaoqiangli
 * @Date 2022-01-06
 */
public class 第N个泰波那契数1137 {

    public static void main(String[] args) {
        System.out.println(tribonacci(25));
    }

    /**
     * 泰波那契序列 Tn 定义如下： 
     *
     * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     *
     * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
     *
     * 输入：n = 4
     * 输出：4
     * 解释：
     * T_3 = 0 + 1 + 1 = 2
     * T_4 = 1 + 1 + 2 = 4
     *
     * 输入：n = 25
     * 输出：1389537
     *
     * @param n
     * @return
     */
    public static int tribonacci(int n) {
        // 思路 泰波那契数相当于前三项之和
        if(n < 2){
            return n;
        }

        if(n == 2){
            return n - 1;
        }

        // dp[i] 表示 Tn
        int[] dp = new int[n + 1];

        // 初始化 T0 = 0, T1 = 1, T2 = 1
        dp[0] = 0; dp[1] = 1; dp[2] = 1;

        // 状态转移方程： Tn+3 = Tn + Tn+1 + Tn+2

        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i -2] + dp[i - 3];
        }
        return dp[n];
    }
}
