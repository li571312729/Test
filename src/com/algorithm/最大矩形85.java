package com.algorithm;

/**
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
 * 输出：6
 */
public class 最大矩形85 {

    public static void main(String[] args) {
        System.out.println(maximalRectangle(new char[][]{{'0','0','0','0','0','0','1'}, {'0','0','0','0','1','1','1'}, {'1','1','1','1','1','1','1'}
                                                        ,{'0','0','0','1','1','1','1'}}));
    }

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            char[] row = matrix[i];
            for (int j = 0; j < row.length; j++) {
                if(row[j] == '1'){
                    int t = i + 1;
                    int colum = 1;
                    int r = 1;
                    while (t < matrix.length && matrix[t][j] == '1') {
                        colum++;
                        t++;
                    }
                    maxArea = Math.max(maxArea, colum * r);

                    int n = j + 1;
                    int minColumn = colum;
                    while (n < row.length && row[n] == '1') {
                        r++;
                        colum = 1;
                        t = i + 1;
                        while (t < matrix.length && matrix[t][n] == '1') {
                            colum++;
                            t++;
                        }
                        minColumn = Math.min(minColumn, colum);
                        maxArea = Math.max(maxArea, minColumn * r);
                        n++;
                    }
                }
            }
        }
        return maxArea;
    }
}
