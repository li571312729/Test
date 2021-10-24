package com.solution;

import static com.solution.十大经典排序算法.swap;

public class 数组中元素个数 {

    public static void main(String[] args) {
        printArr(new int[]{1, 2, 4, 1, 3, 5}, 6);
    }

    /**
     * 给你一个数组a[n], 数组内元素都是1-n
     * 在时间复杂度ON和空间O1要求下打印数组内每个元素出现次数
     * @param arr
     * @param n
     */
    private static void printArr(int[] arr, int n) {
        int init = 1 - (n + 2);

        for (int i = 0; i < arr.length;) {
            // 如果该位置的值恰好就是放置在该位置
            if(arr[i] - 1 == i){
                arr[i] = init + 1;
                i++;
                continue;
            }

            // 如果该位置的值已经被放置过
            if(arr[i] - 1 < 0){
                i++;
                continue;
            }

            // 如果该位置的值不属于这个位置
            if(arr[i] - 1 != i){
                if(arr[arr[i] - 1] < 0){
                    arr[arr[i] - 1] ++;
                    arr[i] = init;
                    i++;
                    continue;
                }
                int temp = arr[i] - 1;
                swap(arr, i, temp);
                arr[temp] = init + 1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println((i + 1) + " => " + (arr[i] - init));
        }
    }

}
