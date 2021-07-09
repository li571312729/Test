package com.solution;

/**
 * 描述
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 *
 * 给定一个整数数组a,同时给定它的大小n和要找的K(1<=K<=n)，请返回第K大的数(包括重复的元素，不用去重)，保证答案存在。
 *
 * @author lxq
 * @date 2021年07月06日 11:45
 */
public class 寻找第K大 {

    public static void main(String[] args) {

        int kth = findKth(new int[]{10, 10, 9, 9, 8, 7, 5, 6, 4, 3, 4, 2}, 12, 5);
        System.out.println(kth);
    }

    /**
     * 输入：
     * [10,10,9,9,8,7,5,6,4,3,4,2],12,3
     * 返回值：
     * 9
     * @author lxq
     * @date 2021/7/6 11:45
     * @param a
     * @param n
     * @param K
     * @return int
     */
    public static int findKth(int[] a, int n, int K) {
        if(n > 0){
            return unckSort(a, 0, n - 1, K);
        }
        return -1;
    }

    public static int unckSort(int[] array,int low,int high, int k) {
        // 获取一轮比较的中位点
        int middle = getMiddle(array,low,high);

        // 如果右边比中位点大的刚好是 k-1 个，那么返回k即可
        if((high - middle + 1) == k){
            return array[middle];
        }

        // 右边比中位点大的数超过k则在右边进行查找
        if((high - middle + 1) > k){
            return unckSort(array,middle + 1, high, k);    // 对高字表进行递归排序
        }

        // 右边比中位点大的数不够k则在左边进行查找,查找的时候把右边已经包含的去掉，
        if((high - middle + 1) < k){
            return unckSort(array, low, middle - 1, k - (high - middle) - 1);    // 对低字表进行递归排序
        }

        return -1;
    }

    /**
     * 获取中轴位置
     * 即中轴一边都比另一边
     * @author lxq
     * @date 2021/6/16 10:47
     * @param list
     * @param low
     * @param high
     * @return int
     */
    public static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low]; // 数组的第一个值作为中轴（分界点或关键数据）
        while (low < high) {
            while (low < high && list[high] >= tmp) {
                high--;
            }
            list[low] = list[high]; // 比中轴小的记录移到低端
            while (low < high && list[low] <= tmp) {
                low++;
            }
            list[high] = list[low]; // 比中轴大的记录移到高端
        }
        list[low] = tmp; // 中轴记录到尾
        return low; // 返回中轴的位置
    }
}
