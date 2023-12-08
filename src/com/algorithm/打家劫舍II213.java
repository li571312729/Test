package com.algorithm;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 *  示例 1：
 *
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 */
public class 打家劫舍II213 {

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,7,9,3,1}));
    }

    /**
     * 这道题跟打家劫舍198类似，区别在于这次房子是连起来的，那这种我们可以根据偷第一间和最后一间 将房子拆分成 0 -》 n-1 和 1-》 n 两部分，
     * 这个时候计算方式就跟之前一样了，取二者最高值
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return nums[0];
        }

        //dp[i] 表示前i个房间可以偷取的最大金额
        //当前房间需要偷窃时(上一个房间就不能偷窃) dp[i] = dp[i - 2] + nums[i - 1]
        //当前房间不偷窃时   dp[i] = dp[i - 1]

        int[] dp = new int[nums.length];
        dp[1] = nums[0];

        int[] dp1 = new int[nums.length];
        dp1[1] = nums[1];

        for (int i = 2; i <= nums.length - 1; i++) {
            //偷第一间
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            //偷最后一间
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }
        return Math.max(dp[nums.length - 1], dp1[nums.length -1]);
    }
}
