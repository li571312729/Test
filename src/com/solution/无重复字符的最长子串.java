package com.solution;

/**
 * @author lxq
 * @date 2021年08月12日 10:33
 */
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring1("abaab"));
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
    public static int lengthOfLongestSubstring(String s) {
        if(s ==null || s.length() == 0){
            return 0;
        }

        char[] chars = s.toCharArray();
        int max = 0;
        StringBuffer current;
        for (int i = 0; i < chars.length; i++) {
            current = new StringBuffer();
            for (int j = i; j < chars.length; j++) {
                if(current.toString().contains(chars[j] + "")){
                    break;
                }
                current.append(chars[j]);
            }
            max = Math.max(max, current.toString().length());
        }

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

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }
}
