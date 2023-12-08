package com.algorithm;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例2
 *      2,1,1,2
 *      这里最大的是4，即第一个和最后一个，也就是说中间可以隔着n个
 */
public class 打家劫舍198 {

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,7,9,3,1}));
    }

    public static int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        //dp[i] 表示前i个房间可以偷取的最大金额
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];

        //当前房间需要偷窃时(上一个房间就不能偷窃) dp[i] = dp[i - 2] + nums[i - 1]
        //当前房间不偷窃时   dp[i] = dp[i - 1]

        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[nums.length];
    }
}
