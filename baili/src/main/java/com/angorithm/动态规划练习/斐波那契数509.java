package com.angorithm.动态规划练习;

/**
 * @author xiaoqiangli
 * @Date 2022-01-06
 */
public class 斐波那契数509 {

    public static void main(String[] args) {
        System.out.println(fib(4));
    }

    /**
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * @param n
     * @return
     */
    public static int fib(int n) {
        if(n < 2){
            return n;
        }

        // dp[i] 表示fib(n)
        int[] dp = new int[n + 1];

        // 初始化dp，题目里已经说明 F(0) = 0，F(1) = 1
        dp[0] = 0; dp[1] = 1;

        // 状态转移方程 dp[i] = F(n - 1) + F(n - 2)

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i -2];
        }

        return dp[n];
    }


}
