package com.solution;

public class NC59矩阵的最小路径和 {

    public static void main(String[] args) {
        int min = minPathSum(new int[][]{{4, 2, 5, 11}, {3, 12, 3, 6}, {5, 9, 6, 8}, {11, 23, 14, 16}});
        int max = maxPathSum(new int[][]{{4, 2, 5, 11}, {3, 12, 3, 6}, {5, 9, 6, 8}, {11, 23, 14, 16}});
        System.out.println(min + "  ----  " + max);
    }

    /**
     * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，
     * 最后到达右下角的位置，路径上所有的数字累加起来就是路径和，
     * 输出所有的路径中最小的路径和。
     * @param matrix
     * @return
     */
    public static int minPathSum (int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }

        int x = matrix.length, y = matrix[0].length;

        // 1. 创建dp[x][y]
        int[][] dp = new int[x][y];

        // 2. dp[x][y] = min(dp[x-1][y]，dp[x][y-1]) + matrix[x][y] 从后往前推

        // 3. 初始化dp数组，并设置起点值
        dp[0][0] = matrix[0][0];
        // 初始化第一行
        for (int i = 1; i < y; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        // 初始化第一列
        for (int i = 1; i < x; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        print(dp);

        // 4. 遍历求值
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                dp[i][j] = Math.min(dp[i -1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[x - 1][y - 1];
    }

    /**
     * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，
     * 最后到达右下角的位置，路径上所有的数字累加起来就是路径和，
     * 输出所有的路径中最大的路径和。
     * @param matrix
     * @return
     */
    public static int maxPathSum (int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }

        int x = matrix.length, y = matrix[0].length;

        // 1. 创建dp[x][y]
        int[][] dp = new int[x][y];

        // 2. dp[x][y] = max(dp[x-1][y]，dp[x][y-1]) + matrix[x][y] 从后往前推

        // 3. 初始化dp数组，并设置起点值
        dp[0][0] = matrix[0][0];
        // 初始化第一行
        for (int i = 1; i < y; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        // 初始化第一列
        for (int i = 1; i < x; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        print(dp);

        // 4. 遍历求值
        for (int i = 1; i < x; i++) {
            for (int j = 1; j < y; j++) {
                dp[i][j] = Math.max(dp[i -1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[x - 1][y - 1];
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
