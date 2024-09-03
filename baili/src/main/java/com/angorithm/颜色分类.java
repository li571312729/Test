package com.angorithm;

import java.util.Arrays;

public class 颜色分类 {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,0,1,1,1,2,2,1,0,0};
        colorSort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void colorSort(int[] a) {
        int first = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[first] == 0) {
                first ++;
                continue;
            }

            if (a[i] == 0) {
                a[i] = a[first];
                a[first] = 0;
                first ++;
            }
        }
        System.out.println(Arrays.toString(a));

        for (int i = first + 1; i < a.length; i++) {
            if (a[first] == 1) {
                first ++;
                continue;
            }

            if (a[i] == 1) {
                a[i] = a[first];
                a[first] = 1;
                first ++;
            }
        }
    }
}
