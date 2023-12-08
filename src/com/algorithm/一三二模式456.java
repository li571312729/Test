package com.algorithm;


import java.util.*;

/**
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 *
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 */
public class 一三二模式456 {

    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{2,1,4,0,-1,-2,-4,-1,-2}));
        System.out.println(find132pattern1(new int[]{2,1,4,0,-1,-2,-4,-1,-2}));
    }

    public static boolean find132pattern1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();

        for (int k = 2; k < n; ++k) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < n - 1; ++j) {
            if (leftMin < nums[j]) {
                Integer next = rightAll.ceilingKey(leftMin + 1);
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            leftMin = Math.min(leftMin, nums[j]);
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }

        return false;
    }

    public static boolean find132pattern(int[] nums) {
        if(nums.length < 3){
            return false;
        }

        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int j = 1; j < nums.length; j++) {
            queue.add(nums[j]);
        }

        int minIndex = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            queue.remove(nums[i]);
            if(nums[i] < nums[minIndex]){
                minIndex = i;
                continue;
            }

            Iterator<Integer> iterator = queue.iterator();
            while (iterator.hasNext()){
                Integer next = iterator.next();
                if(next < nums[i] && next > nums[minIndex]){
                    System.out.println("找到一个三元组:" + nums[minIndex] + "," + nums[i] + "," + next);
                    return true;
                }

                if(next < nums[minIndex]){
                    break;
                }
            }
        }
        return false;
    }
}
