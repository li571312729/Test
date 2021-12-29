package com.solution;

import java.util.Objects;

/**
 * @author xiaoqiangli
 * @Date 2021-12-28
 */
public class 寻找峰值 {

    public static void main(String[] args) {
        int solve = solve(new int[]{2, 4, 1, 2, 7, 8, 4});
        System.out.println("solve:" + solve);
    }

    /**
     * 山峰元素是指其值大于或等于左右相邻值的元素。给定一个输入数组nums，任意两个相邻元素值不相等，数组可能包含多个山峰。
     * 找到索引最大的那个山峰元素并返回其索引。
     *
     * 假设 nums[-1] = nums[n] = -∞。
     *
     * 如输入[2,4,1,2,7,8,4]时，会形成两个山峰，一个是索引为1，峰值为4的山峰，另一个是索引为5，峰值为8的山峰：
     * 这里返回 索引5
     *
     * 寻找最后的山峰 索引
     * @param a int整型一维数组
     * @return int整型
     */
    public static int solve (int[] a) {
        if(Objects.isNull(a) || a.length == 0){
            return -1;
        }

        int lastPeak = a.length - 1;
        for (int i = lastPeak - 1; i >= 0; i--) {
            if(a[i] < a[lastPeak]){
                break;
            }
            lastPeak = i;
        }
        return lastPeak;
    }
}
