package com.solution;

/**
 * @author lxq
 * @date 2021年06月11日 11:19
 */
public class 二分查找II {

    public static void main(String[] args) {
        int search = search(new int[]{1, 2, 4, 4, 4, 5, 7, 9}, 4);
        System.out.println(search);
    }

    /**
     * 请实现有重复数字的升序数组的二分查找
     * 给定一个 元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的第一个出现的target，如果目标值存在返回下标，否则返回 -1
        输入：
     * [1,2,4,4,5],4
     * 返回值：2

     * @param nums int整型一维数组
     * @param target int整型
     * @return int整型
     */
    public static int search (int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }

        int low = 0;
        int high = nums.length-1;
        int mid = 0;
        while (low <= high){
            mid = (high + low) / 2;

            if(target < nums[mid]){
                high = mid - 1;
            }

            if (target > nums[mid]){
                low = mid + 1;
            }

            if (nums[mid] == target){
                while (mid != 0 && nums[mid - 1] == nums[mid]){
                    mid --;
                }
                return mid;
            }
        }
        return -1;
    }

    public int search1 (int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        int mid = 0;
        while(low <= high){
            mid = low+ (high- low) / 2;
            if(nums[mid] == target){
                while(mid != 0 &&(nums[mid-1] == nums[mid])){
                    mid--;
                }
                return mid;
            }
            else if(nums[mid] > target){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
