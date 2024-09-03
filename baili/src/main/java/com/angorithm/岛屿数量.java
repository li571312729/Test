package com.angorithm;

/**
 * @author lxq
 * @date 2021年09月03日 10:05
 */
public class 岛屿数量 {

    public static void main(String[] args) {
        int count = solve(new char[][]{{1, 1, 0, 0, 0}, {0, 1, 0, 1, 1}, {0, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 1, 1}});
        System.out.println(count);
    }

    /**
     * 判断岛屿数量
     *
     * 给一个01矩阵，1代表是陆地，0代表海洋， 如果两个1相邻，那么这两个1属于同一个岛。
     * 我们只考虑上下左右为相邻。
     * 岛屿: 相邻陆地可以组成一个岛屿（相邻:上下左右） 判断岛屿个数。
     *
     * 搜索算法，dfs deepFirstSearch深度优先搜索，bfs 广度优先搜索
     *
     * @param grid char字符型二维数组
     * @return int整型
     */
    public static int solve (char[][] grid) {
        int sum = 0;

        // 先采用dfs深度遍历搜索
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 0){
                    continue;
                }

                // 当遇到一个为1的时候说明遇到岛屿了，接下来只要
                // 递归遍历该岛屿上下左右所有岛，然后将其置为0即可
                sum ++;
                dfs(grid, i, j);
            }
        }
        return sum;
    }

    /**
     * 递归遍历某个节点上下左右的节点
     * @author lxq
     * @date 2021/9/3 11:30
     * @param grid
     * @param i
     * @param j
     */
    private static void dfs(char[][] grid, int i, int j) {
        // 确定移动时边界条件
        int w = grid.length;
        if(i >= w || i < 0){
            return;
        }
        int h = grid[i].length;
        if (j >= h || j < 0) {
            return;
        }

        // 如果经过边界考验则该点符合岛条件
        if(grid[i][j] == 1){
            grid[i][j] = 0;
            // 递归上下左右
            dfs(grid,i - 1, j);
            dfs(grid,i + 1, j);
            dfs(grid, i,j + 1);
            dfs(grid, i,j - 1);
        }
    }
}
