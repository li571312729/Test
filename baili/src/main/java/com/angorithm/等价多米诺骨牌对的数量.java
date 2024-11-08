package com.angorithm;

public class 等价多米诺骨牌对的数量 {

    public static void main(String[] args) {
        System.out.println(numEquivDominoPairs(new int[][]{{1,2},{2,1},{3,4},{5,6}}));
    }

    /**
     * 给你一个由一些多米诺骨牌组成的列表 dominoes。
     * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
     * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
     * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
     *
     * 示例：
     * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
     * 输出：1
     *
     * 1 <= dominoes.length <= 40000
     * 1 <= dominoes[i][j] <= 9
     * @param dominoes
     * @return
     */
    public static int numEquivDominoPairs(int[][] dominoes) {
        int[] arr = new int[100];
        int result = 0;

        for (int[] dominoe : dominoes) {
            int temp = dominoe[0] > dominoe[1] ? 10 * dominoe[0] + dominoe[1] : 10 * dominoe[1] + dominoe[0];
            // 先赋值再加，是因为 我们算的是重复的个数，而不是第一个填入相应位置的
            result += arr[temp];
            arr[temp] ++;
        }
        return result;
    }
}
