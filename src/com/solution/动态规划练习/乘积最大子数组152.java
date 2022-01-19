package com.solution.动态规划练习;

import com.algorithm.Solution;

import java.util.Arrays;

/**
 * @author xiaoqiangli
 * @Date 2022-01-19
 */
public class 乘积最大子数组152 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{0,1,-2,-3,-4}));
    }

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
     * 并返回该子数组所对应的乘积。
     *
     * 示例 1:
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     *
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        int[] min = new int[length];

        System.arraycopy(nums, 0, dp, 0, length);
        System.arraycopy(nums, 0, min, 0, length);

        // dp[i] = Mtah.max(num[i], dp[i - 1] * nums[i]);  要么nums[i] 加入前面一段，要么nums[i]自成一段，
        // 这个跟背包问题不同，需要连续，而不是任选
        // 最后 由于负负得正，，因此，，我们再定义一个数组专门存放最小值，，这样，有可能后续负负得正。

        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(dp[i - 1] * nums[i], Math.max(nums[i], min[i - 1] * nums[i] ));
            min[i] = Math.min(min[i - 1] * nums[i], Math.min(nums[i], dp[i - 1] * nums[i]));
            System.out.println(Arrays.toString(dp));
        }
        
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
