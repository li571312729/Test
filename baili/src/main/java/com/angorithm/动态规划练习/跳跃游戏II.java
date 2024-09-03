package com.angorithm.动态规划练习;

import java.util.Arrays;

/**
 * @author lxq
 * @date 2021年10月14日 11:42
 */
public class 跳跃游戏II {

    public static void main(String[] args) {
        int count = jump(new int[]{2,3,0,1,4});
        System.out.println(count);
    }

    /**
     * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 假设你总是可以到达数组的最后一个位置。
     *
     * @author lxq
     * @date 2021/10/14 11:42
     * @param nums
     * @return int
     */
    public static int jump(int[] nums) {

        int length = nums.length;

        // dp[i] 表示从下标0出发跳跃到下标i需要的最少次数
        int[] dp = new int[length];

        // dp[] 数组初始化每一位为最大值，意味着很难跳到
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // dp[j] = Math.min(dp[j], dp[i] + 1)；

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length ; j++){
                // 如果当前j的位置由i无法直达则直接下一轮
                if(j - i > nums[i]){
                    break;
                }

                if (j == length - 1) {
                    return Math.min(dp[j], dp[i] + 1);
                }
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
            System.out.println(Arrays.toString(dp));
        }

        return dp[length - 1];
    }
}
