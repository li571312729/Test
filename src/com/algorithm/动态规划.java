package com.algorithm;

/**
 * @author Administrator
 */
public class 动态规划 {

    public static void main(String[] args) {
        int i = maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i);
    }

    /**
     * 求连续子序列最大值
     * @param nums
     */
    public static int maxSubArray(int[] nums){
        // 负无穷
        float current_sum = Float.NEGATIVE_INFINITY;
        float best_sum = Float.NEGATIVE_INFINITY;

        for (int num : nums) {
            current_sum = Math.max(num, num + current_sum);
            best_sum = Math.max(current_sum, best_sum);
        }
        return (int) best_sum;
    }

}
