package com.solution;

import com.algorithm.Solution;

/**
 * @author xiaoqiangli
 * @Date 2021-12-31
 */
public class 搜索二维矩阵II240 {

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));
        //System.out.println(searchMatrix(new int[][]{{1, 4}, {2, 5}}, 2));
    }


    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
     * 输出：true
     *
     * 我们可以从矩阵 \textit{matrix}matrix 的右上角 (0, n-1)(0,n−1) 进行搜索。在每一步的搜索过程中，如果我们位于位置 (x, y)(x,y)，
     * 那么我们希望在以 \textit{matrix}matrix 的左下角为左下角、以 (x, y)(x,y) 为右上角的矩阵中进行搜索，
     * 即行的范围为 [x, m - 1][x,m−1]，列的范围为 [0, y][0,y]：
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }
}
