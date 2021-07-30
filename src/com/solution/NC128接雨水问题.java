package com.solution;

public class NC128接雨水问题 {

    public static void main(String[] args) {
        long l = maxWater(new int[]{3,1,2,5,2,4});
        System.out.println(l);
    }

    /**
     * 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个柱子高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 输入：[3,1,2,5,2,4]   返回：5
     *
     * 解法：双指针
     * 第一个指针在第一个元素，第二个指针向后移动，每次计算一格水存储量,
     * 整个数组从左到右一次，然后从右到左一次，防止两边没有闭合
     *
     * @author lxq
     * @date 2021/7/27 18:38
     * @param arr
     * @return long
     */
    public static long maxWater (int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = 0;
        long sum = 0;
        long tmp = 0;
        //从左向右
        for (int i = 1; i < arr.length; i++) {
            if (arr[first] > arr[i]) {
                tmp += arr[first] - arr[i];
            }
            if (arr[first] <= arr[i]) {
                sum += tmp;
                tmp = 0;
                first = i;
            }
        }
        first = arr.length-1;
        tmp = 0;
        // 从右向左
        for (int j = arr.length - 1; j >= 0; j--) {
            if (arr[first] > arr[j]) {
                tmp += arr[first] - arr[j];
            }
            //注意这里不能再 <=，否则可能会重复计算等于的情况
            if (arr[first] < arr[j]) {
                sum += + tmp;
                tmp = 0;
                first = j;
            }
        }
        return sum;
    }
}
