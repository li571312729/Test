package com.angorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author lxq
 * @date 2021年10月14日 14:36
 */
public class 滑动窗口最大值 {

    public static void main(String[] args) {
        int[] ints = maxSlidingWindow(new int[]{2, 3, 2, -2, 2, 5, 2, 1, 6, 2, 2}, 3);
        System.out.println(Arrays.toString(ints));
    }

    /*
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
     * 滑动窗口每次只向右移动一位。
        返回滑动窗口中的最大值。

     * @author lxq
     * @date 2021/10/14 14:37
     * @param null
     * @return null
     * 2, 3, 2, -2, 2, 5, 2, 1, 6, 2, 2
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        int[] date = new int[length - k + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2) -> {
            if(o1.intValue() == o2.intValue()){
                return 0;
            }
            if(o1.intValue() > o2.intValue()){
                return -1;
            }
            if(o1.intValue() < o2.intValue()){
                return 1;
            }
            return 0;
        });
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        date[0] = pq.peek();
        for (int i = k; i < length; i++) {
            if(nums[i] == nums[i - k]){
                date[i - k + 1] = date[i - k];
                continue;
            }
            pq.remove(nums[i - k]);
            pq.offer(nums[i]);
            date[i - k + 1] = pq.peek();
        }
        return date;
    }

}
