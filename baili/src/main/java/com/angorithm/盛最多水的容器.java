package com.angorithm;

/**
 * @author xiaoqiangli
 * @Date 2021-11-17
 */
public class 盛最多水的容器 {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水
     *
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     *
     * 其实就是间隔为1，每个柱子就当做一个挡板，看看那两个挡板中间能放水更多
     *
     * @param arr
     * @return
     *
     *
     *      *               *
     *      *   *           *
     *      *   *   *       *   {4,3,2,1,4}
     *      *   *   *   *   *  = 4 * 4 = 16
     */
    public static int maxArea(int[] arr) {
        int length = arr.length;
        int first = 0, last = length - 1;
        int max = 0;

        // 双指针，一个在前，一个在后计算当前面积，元素小的指针移动，
        // 这里有个优化点(懒得做)，可以一次把小的那个指针移动到第一个大于他的（注意移动的时候判断是否相遇）
        while (first < last){
            if(arr[first] < arr[last]){
                max = Math.max(max, arr[first] * (last - first));
                first ++;
                continue;
            }

            if(arr[first] == arr[last]){
                max = Math.max(max, arr[first] * (last - first));
                first++;
                last--;
                continue;
            }

            if(arr[first] > arr[last]){
                max = Math.max(max, arr[last] * (last - first));
                last--;
                continue;
            }
        }
        return max;
    }
}
