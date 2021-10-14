package com.solution;


import java.io.IOException;
import java.util.Arrays;

import static com.solution.十大经典排序算法.swap;

/**
 * @author lxq
 * @date 2021年07月28日 17:40
 */
public class Test {


    public static void main(String[] args) throws IOException {
        int[] a = {3, 2, 1, 6, 4, 7, 12, 5, 5, 23, 45, 13, 24};
        quickSort(a, 0, 12);
        System.out.println(Arrays.toString(a));
    }

    private static void quickSort(int[] a, int low, int high) {
        if(low < high){
            int middle = getMiddle(a, low, high);
            quickSort(a, low, middle);
            quickSort(a, middle + 1, high);
        }
    }

    private static int getMiddle(int[] a, int low, int high) {
        while (low < high){
            // 从high向前找为标记位小的元素
            while (low < high && a[low] <= a[high]){
                high--;
            }
            if(low < high){
                swap(a, low, high);
            }

            // 从low找一个比标记位大的元素
            while (low < high && a[low] <= a[high]){
                low ++;
            }
            if(low < high){
                swap(a, low, high);
            }
        }

        return low;
    }


}
