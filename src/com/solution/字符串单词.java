package com.solution;

import java.util.Arrays;
import java.util.List;

/**
 * @author lxq
 * @date 2021年10月14日 9:34
 */
public class 字符串单词 {

    public static void main(String[] args) {
        List<String> dict = Arrays.asList("applepen".split(","));
        boolean applepen = compositionAbleString(dict, "applepen");
        System.out.println(applepen);
    }

    /*
     * 在单词列表dict中查看字符串能否被其中某些单词组成
     * @author lxq
     * @date 2021/10/14 9:44
     * @param dict
     * @param applepen 
     */
    private static boolean compositionAbleString(List<String> dict, String target) {

        int n = target.length();

        // dp[i] 表示的是从下标0到i的字符串能否由字典中某些单词组成
        boolean[] dp = new boolean[n + 1];

        // dp数组默认初始化为false即可
        dp[0] = true;

        // dp[j] = dp[i] && dict.contains(target.substring(i, j + 1))

        for(int i = 0; i < n; i++){
            for(int j = i; dp[i] && j < n; j++){
                if(dict.contains(target.substring(i, j + 1))){
                    dp[j + 1] = true;
                }
            }
        }

        return dp[n];
    }


}
