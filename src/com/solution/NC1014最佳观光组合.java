package com.solution;

import com.algorithm.Solution;

/**
 * @author xiaoqiangli
 * @Date 2022-03-08
 */
public class NC1014最佳观光组合 {

    public static void main(String[] args) {
        System.out.println(maxScoreSightseeingPair(new int[]{8,1,5,2,6}));
    }


    /**
     * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
     *
     * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
     *
     * 返回一对观光景点能取得的最高分。
     *
     * 2 <= values.length <= 5 * 104
     * 1 <= values[i] <= 1000
     *
     *
     * 解题思路：
     * 普通的双循环时间复杂度是在On2，超过限制，尝试降低循环，，观察 表达式 values[i] + values[j] + i - j
     * 将之拆分为 values[i] + i 和 values[j] - j两部分，且 i 是在j前面的，也就是说 我们一遍循环初始时 i == 0，j==1 可以计算出第一对
     * 景点的得分，，这时候顺手 用一个变量表示 m = values[i] + i 其中i == 1，当j等于2的时候，，m就是1对应的 景点分值，，相当于 j是固定的，，只有m在变，，，
     *
     *
     * @param values
     * @return
     */
    public static int maxScoreSightseeingPair(int[] values) {
        int ans = 0, mx = values[0] + 0;
        for (int j = 1; j < values.length; ++j) {
            ans = Math.max(ans, mx + values[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }


    public static int maxScoreSightseeingPairTest(int[] values) {
        int score = 0;

        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                int tempScore = values[i] + values[j] + i - j;
                score = Math.max(score, tempScore);
            }
        }
        return score;
    }
}
