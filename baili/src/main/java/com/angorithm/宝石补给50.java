package com.angorithm;

import java.util.Arrays;

/**
 * 欢迎各位勇者来到力扣新手村，在开始试炼之前，请各位勇者先进行「宝石补给」。
 *
 * 每位勇者初始都拥有一些能量宝石， gem[i] 表示第 i 位勇者的宝石数量。现在这些勇者们进行了一系列的赠送，operations[j] = [x, y] 表示在第 j 次的赠送中 第 x 位勇者将自己一半的宝石（需向下取整）赠送给第 y 位勇者。
 *
 * 在完成所有的赠送后，请找到拥有最多宝石的勇者和拥有最少宝石的勇者，并返回他们二者的宝石数量之差。
 *
 * 注意：
 *
 * 赠送将按顺序逐步进行。
 *
 * 示例 1：
 *
 * 输入：gem = [3,1,2], operations = [[0,2],[2,1],[2,0]]
 *
 * 输出：2
 *
 * 解释： 第 1 次操作，勇者 0 将一半的宝石赠送给勇者 2， gem = [2,1,3] 第 2 次操作，
 * 勇者 2 将一半的宝石赠送给勇者 1， gem = [2,2,2] 第 3 次操作，勇者 2 将一半的宝石赠送给勇者 0， gem = [3,2,1] 返回 3 - 1 = 2
 */

public class 宝石补给50 {

    public static void main(String[] args) {
        System.out.println(giveGem(new int[]{3,1,2}, new int[][]{{0,2}, {2,1}, {2,0}}));
    }

    public static int giveGem(int[] gem, int[][] operations) {
        for (int[] ar : operations) {
            int count = gem[ar[0]] / 2;
            gem[ar[0]] -= count;
            gem[ar[1]] += count;
        }
        Arrays.sort(gem);
        return gem[gem.length - 1] - gem[0];
    }
}
