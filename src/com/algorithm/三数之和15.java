package com.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和15 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }

    /**
     * -4 -1 -1 0 1 2
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //小优化，因为排好序，如果当前是>0的，则后续没必要找了
            if(nums[i] > 0){
                return result;
            }

            // 需要和上一次枚举的数不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }


            int temp = -nums[i];
            int last = nums.length - 1;
            for (int j = i + 1; j < last;) {
                if((nums[j] + nums[last]) < temp){
                    j ++;
                }else if((nums[j] + nums[last]) == temp){
                    //去除重复，i固定的情况下防止出现-2,0,0,2,2 这种相同情况
                    if(!(j > i + 1 && nums[j] == nums[j - 1])){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[last]);
                        result.add(list);
                    }
                    j ++;
                    last --;
                }else if((nums[j] + nums[last]) > temp){
                    last --;
                }
            }
        }
        return result;
    }
}
