package com.angorithm;

import java.util.ArrayList;
import java.util.List;

public class 划分字母区间 {

    public static void main(String[] args) {
        划分字母区间 a = new 划分字母区间();
        a.partitionLabels("abaccbdeffed");
    }

    /**
     * a b a c c b d e f f e  d
     *     2   4 5       9 10 11
     *
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if(S == null || "".equals(S)){
            return result;
        }

        // 存放最后每个字母最后位置的数组
        int[] a = new int[26];
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            a[chars[i] - 97] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            int lastIndex = a[chars[i] - 97];
            for(int j = i + 1; i < lastIndex; i++){
                if(a[chars[j] - 97] > j){

                }

            }
        }




        return result;

    }
}
