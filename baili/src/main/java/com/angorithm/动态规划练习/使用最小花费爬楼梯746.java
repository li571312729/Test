package com.angorithm.动态规划练习;

/**
 * @author xiaoqiangli
 * @Date 2022-01-06
 */
public class 使用最小花费爬楼梯746 {

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
    }

    /**
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     *
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     *
     * 请你计算并返回达到楼梯顶部的最低花费。
     *
     * 输入：cost = [10,15,20]
     * 输出：15
     * 解释：你将从下标为 1 的台阶开始。
     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
     * 总花费为 15 。
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {
        if(cost.length < 1){
            return 0;
        }
        if(cost.length == 1){
            return cost[0];
        }

        // dp[i] 表示到达该层的最小花费
        int[] dp = new int[cost.length + 1];

        // 状态转移方程，第i个阶梯只有踩或者不踩
        // 如果踩 dp[i] = min(cost[i - 1] + dp[i - 1] / cost[i - 2] + dp[i - 2])

        // 因为我们可以选择第0或者第1层开始，因此 初始化 dp[0] = 0, dp[1] = 0;
        dp[0] = 0; dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }
}
