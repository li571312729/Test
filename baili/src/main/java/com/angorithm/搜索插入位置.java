package com.angorithm;

/**
 * @author bali
 */
public class 搜索插入位置 {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     * 示例 1:
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            int middle = (left + right) / 2;
            if(nums[middle] > target){
                right = middle - 1;
            }else if(nums[middle] == target){
                return middle;
            }else if(nums[middle] < target){
                left = middle + 1;
            }
        }
        return right + 1;
    }
}
