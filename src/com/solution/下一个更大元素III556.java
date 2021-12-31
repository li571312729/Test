package com.solution;

import okio.Utf8;

import java.util.Random;

/**
 * @author xiaoqiangli
 * @Date 2021-12-30
 */
public class 下一个更大元素III556 {

    public static void main(String[] args) {
        int i = nextGreaterElement(4752);
        System.out.println(i);
    }

    /**
     * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。
     *
     * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
     *
     * 示例 1：
     *
     * 输入：n = 12
     * 输出：21
     * 示例 2：
     *
     * 输入：n = 21
     * 输出：-1
     *
     * @param n
     * @return
     */
    public static int nextGreaterElement(int n) {
        String m = String.valueOf(n);
        char[] x = m.toCharArray();

        /**
         * 思路：从后往前找到第一个小于他后面第一个元素的元素 X（这里指索引），然后再从后往前找到第一个大于刚才那个元素X的元素 Y（这里指索引），
         * 交换X，Y对应的值，同时将X后面的元素从降序（因为Y就是第一个大于X的，所以交换后仍然为升序）变为升序即可。
         */

        for (int i = x.length - 2; i >= 0; i--) {
            // i 的位置就是 上面说的X
            if(x[i] < x[i + 1]){
                // 找到Y的位置就是这里的 j，然后交换
                for (int j = x.length - 1; j > i; j--){
                    if(x[j] > x[i]){
                        x[j] ^= x[i];
                        x[i] ^= x[j];
                        x[j] ^= x[i];
                        break;
                    }
                }

                // 利用双指针将降序变为升序即可
                for(int j = x.length - 1, k = i + 1; j > k; k++, j--){
                    x[j] ^= x[k];
                    x[k] ^= x[j];
                    x[j] ^= x[k];
                }
                long data = Long.parseLong(new String(x));
                return data > Integer.MAX_VALUE ? -1 : (int)data;
            }
        }
        return -1;
    }
}
