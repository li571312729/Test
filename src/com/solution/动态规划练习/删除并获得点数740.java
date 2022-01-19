package com.solution.动态规划练习;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoqiangli
 * @Date 2022-01-06
 */
public class 删除并获得点数740 {

    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{3, 1}));
    }


    /**
     * 给你一个整数数组 nums ，你可以对它进行一些操作。
     *
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
     *
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     *
     * 输入：nums = [3,4,2]
     * 输出：6
     * 解释：
     * 删除 4 获得 4 个点数，因此 3 也被删除。
     * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
     *
     * nums = [2,2,3,3,3,4]
     * @param nums
     * @return
     */
    public static int deleteAndEarn(int[] nums) {
        /**
         * 这个题我们可以设置一个新数组统计所有相同值的累加和，然后就像
         * 打家劫舍一样从第一个数选到最后一个数字即可，本题和打家劫舍类似
         * 都是间隔选择
         */

        int result= 0;
        if(nums.length == 0){
            return result;
        }

        if(nums.length == 1){
            return nums[0];
        }

        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        int[] data = new int[max + 1];
        for (int num : nums) {
            data[num] += num;
        }

        // dp[i] 表示从从第一个数删到第i个数字的最大值
        int[] dp = new int[max + 1];

        /**
         * 计算dp[i]的时候有2种情况：
         *      1.第i个数字不删 则当前值就是 dp[i - 1]的最大值
         *      2.第i个数字删除，那就是dp[i- 2] + data[i]的值
         */
        dp[1] = data[1];

        for (int i = 2; i < data.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i- 2] + data[i]);

        }
        return dp[max];
    }
}
