package com.angorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoqiangli
 * @Date 2021-11-16
 */
public class 组合总和 {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{2,7,6,3,5,1}, 9);
        lists.stream().forEach(System.out::println);
    }



    /**
     * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
     * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
     * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个
     * 输入: candidates = [2,3,6,7], target = 7
     * 输出: [[7],[2,2,3]]
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        flashBack(result, new ArrayList<Integer>(), 0, candidates, target, 0);
        return result;
    }

    /**
     *
     *  这道题一眼看过去有点全排列和完全背包的意味，因此我们可以在全排列的回溯算法中加入处理完全背包的思路，
     *  从头遍历数组，每次选择一个元素后开启新一轮迭代，但是这时迭代起始位置不变，这样可以重复选择元素，
     *  如果当前元素无法选择，则向后寻找能够选择的元素，当找到一个元素时，需要将该元素下标赋值给迭代起始位置，
     *  这样当该元素后续被回溯时，我们依然可以从这个迭代轮次的后续位置继续探索，直到主迭代中所有元素都被探索完。
     *
     * @param result 最后要返回的总数据集
     * @param temp 回溯过程中的已选择的元素的临时总和
     * @param tempSum 回溯过程中的已选择的元素的临时集合
     * @param target 目标值
     * @param index 回溯过程中的开始原数组索引下标
     */
    private static void flashBack(List<List<Integer>> result, ArrayList<Integer> temp, int tempSum, int[] candidates, int target, int index) {

        // 终止条件，如果数字合等于target，说明找到了一个排列
        // 该排列表示n叉树到叶子节点的遍历
        if(tempSum == target){
            result.add(new ArrayList<>(temp));
            return;
        }

        // 遍历数据集合，相当于遍历n叉树，这里要注意每次都从0开始，所以注意过滤重复的
        for (int i = index; i < candidates.length; i++) {
            if(target < (tempSum + candidates[i])){
                continue;
            }
            index = i;
            temp.add(candidates[i]);
            tempSum += candidates[i];

            // 递归遍历子节点
            flashBack(result, temp, tempSum, candidates, target, index);

            // 回溯的精华：撤销当前选择
            tempSum -= temp.get(temp.size() - 1);
            temp.remove(temp.size() - 1);
            index++;
        }
    }
}
