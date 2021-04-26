package com.algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class 无重叠区间 {

    public static void main(String[] args) {
        无重叠区间 a = new 无重叠区间();
        System.out.println(a.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));;
    }

    /**
     给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     注意:
     可以认为区间的终点总是大于它的起点。
     区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     示例 1:
     输入: [ [1,2], [2,3], [3,4], [1,3] ]
     输出: 1
     解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

        // 1,2  1,3  2,3  3,4
        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[j][1] <= intervals[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }
}
