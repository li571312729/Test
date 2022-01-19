package com.solution.动态规划练习;

/**
 * @author lxq
 * @date 2021年10月14日 11:10
 */
public class 跳跃游戏 {

    public static void main(String[] args) {
        boolean b = canJump(new int[]{3,2,1,0,4});
        System.out.println(b);
    }

    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个下标。
     * @author lxq
     * @date 2021/10/14 11:10
     * @param nums
     * @return null
     */
    public static boolean canJump(int[] nums) {

        int length = nums.length;

        // dp[i] 表示0到i能否跳到
        boolean[] dp = new boolean[length];

        // dp[] 默认初始化为false即可
        dp[0] = true;

        // dp[j] = dp[i] && j - i < nums[i]

        for (int i = 0; i < length; i++) {
            for (int j = i; dp[i] && j < length ; j++){
                if(j - i <= nums[i]){
                    dp[j] = true;
                }
            }
        }
        return dp[length - 1];
    }
}
