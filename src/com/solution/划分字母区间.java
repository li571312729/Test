package com.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoqiangli
 * @Date 2021-12-22
 */
public class 划分字母区间 {

    public static void main(String[] args) {
        partitionLabels("ababcbacadefegdehijhklij").stream().forEach(System.out::println);
    }

    /**
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
     * 同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
     *
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。
     *
     * @param s
     * @return
     */
    public static List<Integer> partitionLabels(String s) {
        /**
         * 第一次循环先得到每个字符出现的最大位置
         * 第二次循环，从头开始如果当前字符最大值超过临时变量最大值，则替换，
         * 如果一直到最大值都没有超过的，则直接添加最大和最小之间的间隔即可
         */
        List<Integer> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], i);
        }

        int max = 0;
        int min = 0;
        for (int i = 0; i < chars.length; i++) {
            max = Math.max(max, map.get(chars[i]));
            if(i >= max){
                list.add(max -min + 1);
                min = i + 1;
            }
        }
        return list;
    }
}
