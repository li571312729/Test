package com.angorithm;

/**
 * 给你一个非负整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 */
public class 目标和494 {

    static int count;

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1,1,1,1,1}, 3));
    }

    /**
     * 使用回溯的方式来做，每个元素只有+ / - 两种形式
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays(int[] nums, int target) {
        findTarget(nums, target, 0, 0);
        return count;
    }

    public static void findTarget(int[] nums, int target, int index, int sum) {
        if(index == nums.length) {
            if(sum == target){
                count ++;
                return;
            }
        }else {
            findTarget(nums, target, index + 1, sum + nums[index]);
            findTarget(nums, target, index + 1, sum - nums[index]);
        }
    }
}
