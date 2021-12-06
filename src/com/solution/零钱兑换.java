package com.solution;

import java.util.Arrays;

/**
 * @author xiaoqiangli
 * @Date 2021-12-6
 */
public class 零钱兑换 {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2}, 3));
    }

    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     *
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     *
     * 你可以认为每种硬币的数量是无限的。
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        // dp[i] 表示兑换i需要的硬币数量
        int[] dp = new int[amount + 1];

        // dp[i] = math.min(dp[i], dp[i - j] + 1)

        Arrays.fill(dp, amount + 1);
        dp[0] = 0;


        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                //如果当前的钱币比目标值小就可以兑换
                if(i >= coins[j]){
                    // dp[i-arr[j]] + 1 表示，如果我想用这张纸币进行兑换，则我必须拥有目标值减去该纸币对应的剩下的兑换方案。
                    // 如果有的话，那就说明这张纸币可以用来兑换，则新的数目就等于 （目标值减去该纸币对应的剩下的兑换方案 + 1（1 就是这张纸币））
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
