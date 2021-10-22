package com.solution;

import com.algorithm.Solution;

/**
 * @author lxq
 * @date 2021年10月15日 16:41
 */
public class 最长有效括号 {

    public static void main(String[] args) {
        
        
    }

    /**
     * 给你一个只包含 `'('` 和 `')'` 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * 输入： （（）
     * 输出： 2
     * @author lxq
     * @date 2021/10/15 16:41
     * @param s 
     * @return int
     */
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
