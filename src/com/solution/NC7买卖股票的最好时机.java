package com.solution;

import org.apache.poi.util.Beta;

/**
 * @author lxq
 * @date 2021年07月21日 10:51
 */
public class NC7买卖股票的最好时机 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1}));
    }

    /**
     * 假设你有一个数组，其中第\ i i 个元素是股票在第\ i i 天的价格。
     * 你有一次买入和卖出的机会。（只有买入了股票以后才能卖出）。请你设计一个算法来计算可以获得的最大收益。
     * 示例1
     * 输入：
     * [1,4,2]
     * 返回值：
     * 3
     * @author lxq
     * @date 2021/7/21 10:52
     * @param prices
     * @return int
     */
    public static int maxProfit (int[] prices) {
        int minVal = prices[0], profit = 0;
        for (int price : prices) {
            if (price < minVal) {
                minVal = price;
            }
            profit = Math.max(profit, price - minVal);
        }
        return profit;
    }
}
