package com.angorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * 给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [starti, endi]
 * 表示第 i 朵花的 花期 从 starti 到 endi （都 包含）。
 * 同时给你一个下标从 0 开始大小为 n 的整数数组 people ，people[i] 是第 i 个人来看花的时间。
 *
 * 请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
 *
 * 输入：flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
 * 输出：[1,2,2,2]
 *
 */
public class 花期内花的数目2251 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(fullBloomFlowers1(new int[][]{{1,6}, {3,7}, {9,12}, {4,13}}, new int[]{2,3,7,11})));
    }

    public static int[] fullBloomFlowers1(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        for (int[] flower : flowers) {
            cnt.put(flower[0], cnt.getOrDefault(flower[0], 0) + 1);
            cnt.put(flower[1] + 1, cnt.getOrDefault(flower[1] + 1, 0) - 1);
        }
        int m = people.length;
        Integer[] indices = IntStream.range(0, m).boxed().toArray(Integer[]::new);
        Arrays.sort(indices, (i, j) -> people[i] - people[j]);
        int[] ans = new int[m];
        int curr = 0;
        for (int x : indices) {
            while (!cnt.isEmpty() && cnt.firstKey() <= people[x]) {
                curr += cnt.pollFirstEntry().getValue();
            }
            ans[x] = curr;
        }
        return ans;
    }


    public static int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int[] result = new int[people.length];
        Map<Integer, Integer> seed = new HashMap<>();

        for (int[] flower : flowers) {
            for (int i = flower[0]; i <= flower[1]; i++) {
                seed.put(i, seed.getOrDefault(i, 0) + 1);
            }
        }

        for (int i = 0; i < people.length; i++) {
            result[i] = seed.getOrDefault(people[i], 0);
        }
        return result;
    }
}
