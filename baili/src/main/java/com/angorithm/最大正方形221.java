package com.angorithm;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 */
public class 最大正方形221 {

    public static void main(String[] args) {
        System.out.println(maximalSquare(new char[][]{{'0','1','1','0','0','1','0','1','0','1'},{'0','0','1','0','1','0','1','0','1','0'}
                , {'1','0','0','0','0','1','0','1','1','0'},{'0','1','1','1','1','1','1','0','1','0'}
                ,{'0','0','1','1','1','1','1','1','1','0'},{'1','1','0','1','0','1','1','1','1','0'}
                ,{'0','0','0','1','1','0','0','0','1','0'},{'1','1','0','1','1','0','0','1','1','1'}
                ,{'0','1','0','1','1','0','1','0','1','1'}}));
        System.out.println(maximalSquare1(new char[][]{{'0','1','1','0','0','1','0','1','0','1'},{'0','0','1','0','1','0','1','0','1','0'},{'1','0','0','0','0','1','0','1','1','0'},{'0','1','1','1','1','1','1','0','1','0'},{'0','0','1','1','1','1','1','1','1','0'},{'1','1','0','1','0','1','1','1','1','0'},{'0','0','0','1','1','0','0','0','1','0'},{'1','1','0','1','1','0','0','1','1','1'},{'0','1','0','1','1','0','1','0','1','1'}}));
    }

    /**
     * 暴力解法
     * 我们找到一个1的点，就沿着对角线看下一个节点是否为1，若为1则看下一个节点左侧和上方是否都为1，
     * 然后沿着对角线继续循环即可
     * @param matrix
     * @return
     */
    public static int maximalSquare1(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }

        int maxSquare = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == '0'){
                    continue;
                }

                int ri = i + 1, rj = j + 1;
                int tempSquare = 1;
                retry:
                while (ri < matrix.length && rj < matrix[i].length && matrix[ri][rj] == '1'){
                    for (int n = i; n <= ri; n ++){
                        if(matrix[n][rj] == '0'){
                            break retry;
                        }
                    }

                    for (int n = j; n <= rj; n ++){
                        if(matrix[ri][n] == '0'){
                            break retry;
                        }
                    }
                    tempSquare ++;
                    ri ++;
                    rj ++;
                }
                maxSquare = Math.max(maxSquare, tempSquare);
            }
        }
        return maxSquare * maxSquare;
    }

    /**
     * 动态规划 dp[i][j] 表示组成正方形的右下角坐标，其值表示该正方形的边长，因此 该值应该等于该坐标
     * 左侧，上方，左上方三个节点中最小的dp值加1
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int[][] dp = new int[matrix.length][];
        int maxSide = 0;

        for (int i = 0; i < matrix.length; i++) {
            dp[i] = new int[matrix[i].length];

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    if(i < 1 || j < 1){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i- 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
