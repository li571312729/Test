package com.angorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxq
 * @date 2021年08月12日 10:33
 */
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     输入: s = "abcabcbb"
     输出: 3 
     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     暴力解法：双重循环，也就是从头开始一个元素一个元素的筛选
     * @author lxq
     * @date 2021/8/12 10:33
     * @param s
     * @return int
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s ==null || s.length() == 0){
            return 0;
        }

        char[] chars = s.toCharArray();
        int max = 0;
        String data = "";
        StringBuffer current;
        for (int i = 0; i < chars.length; i++) {
            current = new StringBuffer();
            for (int j = i; j < chars.length; j++) {
                if(current.toString().contains(chars[j] + "")){
                    break;
                }
                current.append(chars[j]);
            }
            if(current.toString().length() > max){
                max = current.toString().length();
                data = current.toString();
            }
        }
        System.out.println(data);
        return max;
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     输入: s = "abcabcbb"
     输出: 3
     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @author lxq
     * @date 2021/8/12 10:33
     * @param s
     * @return int
     */
    public static int lengthOfLongestSubstring1(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int max = 0;
        String data = "";

        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            if(i - start + 1 > max){
                max = i - start + 1;
                data = s.substring(start, i + 1);
            }
            last[index] = i;
        }
        System.out.println(data);
        return max;
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     输入: s = "abcabcbb"
     输出: 3
     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     滑动窗口解决
     * @author lxq
     * @date 2021/8/12 10:33
     * @param s
     * @return int
     */
    public static int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map  = new HashMap<>(16);
        String data = "";
        int max = 0;

        // 滑动窗口左边界
        int start = 0;
        for (int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                Integer index = map.get(s.charAt(i));
                if(i - start > max){
                    max = i - start;
                    data = s.substring(start, i);
                }
                start = index + 1;
            }
            map.put(s.charAt(i), i);
        }

        if(map.size() > max){
            max = map.size();
            data = s.substring(start);
        }
        System.out.println(data);
        return max;
    }
}
