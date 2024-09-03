package com.angorithm;


/**
 * @author xiaoqiangli
 * @Date 2021-12-03
 */
public class 长度最小的子数组 {

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(8, new int[]{1,2,3,4,5}));
    }


    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        // 连续子数组，我们可以使用滑动窗口来做

        if(nums == null || nums.length == 0){
            return 0;
        }

        int l = 0, r = 0, current = nums[0], len = Integer.MAX_VALUE;

        while (true) {
            if(current >= target){
                // 1个大于目标这已经是最短的了。直接返回即可
                if(l == r){
                    len = 1;
                    break;
                }
                len = Math.min(r-l+1, len);
                current -= nums[l++];
            }else {
                // 这里做一个判断是因为如果右边界到头了，但是合值小于target，则左边界没有必要继续走了，越走越小，毕竟正整数。
                if(r == nums.length - 1){
                    break;
                }
                current += nums[++r];
            }
        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }

}
