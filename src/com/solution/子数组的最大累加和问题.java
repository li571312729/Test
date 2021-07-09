package com.solution;

/**
 * @author lxq
 * @date 2021年07月07日 14:25
 */
public class 子数组的最大累加和问题 {

    public static void main(String[] args) {
        int i = maxsumofSubarray(new int[]{1, 2, -3, -5, 2, 6, -1});
        int j = maxsumofSubarray1(new int[]{1, 2, -3, -5, 2, 6, -1});
        System.out.println(i + "  <--->  " + j);
    }

    /**
     * 给定一个数组arr，返回子数组的最大累加和
     * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
     * 题目保证没有全为负数的数据
     *
     * 动态规划法解决 适用任何类型
     * @author lxq
     * @date 2021/7/7 14:26
     * @param arr
     * @return int
     */
    public static int maxsumofSubarray (int[] arr) {
        int count = 0;
        int max = Integer.MIN_VALUE;

        for (int i : arr) {
            count = Math.max(count + i, i);
            max = Math.max(count, max);
        }
        return max;
    }

    /**
     * 将每项之前的结果累加获取一条结果曲线
     * 使用最高点减去最低点
     * 适用于 没有全为负数的数据
     * @author lxq
     * @date 2021/7/7 14:44
     * @param arr 
     * @return int
     */
    public static int maxsumofSubarray1 (int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int a : arr){
            count += a;
            max = Math.max(max, count);
            min = Math.min(min, count);
        }
        return max - min;
    }
}
