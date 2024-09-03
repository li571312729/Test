package com.angorithm;

/**
 * @author lxq
 * @date 2021年08月04日 16:04
 */
public class 最长公共子序列长度 {

    public static void main(String[] args) {

        int s = LCSLength("1A2C3D4B56","B1D23A456A");
        System.out.println(s);

    }

    public static int LCSLength (String s1, String s2) {
        // 创建dp数组，dp[i][j]含义为s1,s2从头开始到i，j为之的最长公共子序列
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // 状态转移方程
        // 当 s1.charAt(s1.length()-1) = s2.charAt(s2.length()-1) 时 dp[i][j] = dp[i-1][j-1] + 1
        // 否则 dp[i][j] = max(dp[i-1][j], dp[i][j-1])

        // dp 初始化 如果为0 可以不用初始化
        for (int i = 0; i < s1.length() + 1; i ++){
            dp[i][0] = 0;
        }
        for (int i = 0; i < s2.length() + 1; i ++){
            dp[0][i] = 0;
        }

        print(dp);

        for (int i = 1; i <= s1.length(); i ++){
            for (int j = 1; j <= s2.length(); j ++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println();
        print(dp);
        return dp[s1.length()][s2.length()];
    }

    public static void print(int[][] dp){
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + "   ");
            }
            System.out.println();
        }
    }
}
