package com.angorithm;

/**
 * @author lxq
 * @date 2021年07月09日 13:40
 */
public class 最长回文子串 {

    public static void main(String[] args) {
        int a = getLongestParlindome("cbc", 3);
        System.out.println(a);
    }

    /**
     * 对于一个字符串，请设计一个高效算法，计算其中最长回文子串的长度。
     * 给定字符串A以及它的长度n，请返回最长回文子串的长度。
     * @author lxq
     * @date 2021/7/9 13:40
     * @param A
     * @param n
     * @return int
     *
     *
        "abc1234321ab",12
        7
     */
    public static int getLongestParlindome(String A, int n) {
        if (n <= 1){
            return n;
        }

        int max = 1;
        boolean[][] data = new boolean[n][n];
        char[] chars = A.toCharArray();

        for (int j = 1; j <= n - 1; j ++){
            for (int i = 0; i < j; i ++ ){
                data[i][j] = chars[i] == chars[j] && (j - i < 3 || data[i + 1][j - 1]);

                if(data[i][j]){
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

}
