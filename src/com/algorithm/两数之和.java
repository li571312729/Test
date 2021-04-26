package com.algorithm;

import java.util.HashMap;
import java.util.Map;

public class 两数之和 {

    public static void main(String[] args) {
        两数之和 a = new 两数之和();
        int[] ints = a.twoSum(new int[]{1, 5, 4, 6, 2, 7, 8}, 15);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            if(map.containsKey(target - nums[i])){
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
