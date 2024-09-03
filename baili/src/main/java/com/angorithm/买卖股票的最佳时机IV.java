package com.angorithm;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author baili
 */
public class 买卖股票的最佳时机IV {

    public static void main(String[] args) {
        // 4, 3, 5
        System.out.println(maxProfit(2, new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }

    /**
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1：
     * 输入：k = 2, prices = [2,4,1]
     * 输出：2
     * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit(int k, int[] prices) {
        if(k <= 0 || prices == null || prices.length == 0){
            return 0;
        }

        // 存储最大的K笔交易赚取金额
        List<Integer> result = new ArrayList<>(prices.length / 2);

        // 使用快慢指针确定增值空间
        int low = 0;
        for (int i = 0, j = 1; j < prices.length;) {
            if (prices[j] < prices[i] || j == prices.length - 1) {
                int end = j - 1;
                if(j == prices.length - 1 && prices[j] > prices[i]){
                    end = j;
                }

                int diff = prices[end] - prices[low];
                if(diff > 0){
                    result.add(diff);
                }
                low = j;
            }
            j++;
            i++;
        }

        result.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        return result.stream().limit(k).mapToInt(Integer::intValue).sum();
    }


}
