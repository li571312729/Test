package com.angorithm;

import java.util.ArrayList;
import java.util.List;

public class 重构2行二进制矩阵 {

    public static void main(String[] args) {
        int[] a = new int[]{0,1,2,0,0,0,0,0,2,1,2,1,2};
        List<List<Integer>> lists = reconstructMatrix(9, 2, a);
        lists.stream().forEach(o -> o.stream().forEach(System.out::println));
    }

    public static List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> result = new ArrayList<>(2);
        List<Integer> one = new ArrayList<>(colsum.length);
        List<Integer> two = new ArrayList<>(colsum.length);
        result.add(one);
        result.add(two);

        int sum = upper + lower;
        // 第一遍循环先处理列等于2的，同时记录总列和 ，后面判断能不能构造
        for (int i = 0; i < colsum.length; i++) {
            sum -= colsum[i];
            one.add(0);
            two.add(0);
            if(colsum[i] == 2){
                one.set(i, 1);
                two.set(i, 1);
                upper --;
                lower --;
            }
        }

        // 列的和值应该等于 upper + lower,否则不能构造这个二进制矩阵
        if (sum != 0) {
            return new ArrayList<>();
        }

        //第二遍循环处理剩余的列植，分配到两行上
        for (int i = 0; i < colsum.length; i++) {
            if(colsum[i] == 1){
                if(upper > 0){
                    one.set(i, 1);
                    upper --;
                }else {
                    two.set(i, 1);
                    lower --;
                }
            }
        }

        if (lower < 0) {
            return new ArrayList<>();
        }
        return result;
    }
}
