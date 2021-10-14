package com.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lxq
 * @date 2021年09月03日 9:35
 */
public class 全排列问题 {

    public static void main(String[] args) {
        List<List<Integer>> permute = permute(new int[]{1, 2, 3, 5});
        System.out.println(Arrays.deepToString(permute.toArray()));
    }

    /**
     * 给定一个不重复的整数集合，求所有的排列结果
     * 回溯法--(模拟二叉树的遍历，每一层在上一层选择了某值的情况下得到下一层)
     * @author lxq
     * @date 2021/9/3 9:36
     * @param nums
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> list= new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums) {
        // 终止条件，如果数字都被用完了，说明找到了一个排列
        // 该排列表示n叉树到叶子节点的遍历
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
            return;
        }

        // 遍历数据集合，相当于遍历n叉树，这里要注意每次都从0开始，所以注意过滤重复的
        for (int i = 0; i < nums.length; i++) {
            // 重复的就跳过
            if(tempList.contains(nums[i])){
                continue;
            }

            // 不重复就选择当前值
            tempList.add(nums[i]);

            // 递归遍历子节点
            backtrack(list, tempList, nums);

            // 回溯的精华：撤销当前选择
            tempList.remove(tempList.size() - 1);
        }
        

    }


}
