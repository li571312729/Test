package com.solution;

import java.util.LinkedList;

/**
 * @author lxq
 * @date 2021年08月20日 17:36
 */
public class CardMove {
    /**
     * 1. 从牌顶拿出一张牌，放到桌子上
     * 2. 从牌顶拿出一张牌，放在牌的底部
     * 3. 重复第一步，第二步操作，知道所有的牌都放到了桌子上
     *
     * 问：已知桌子上牌的顺序是1,2,3,4,5,6,7,8,9,10,11,12,13
     * 牌原来的顺序是什么
     *
     * 分析：如果这个操作倒着来
     * 1. 从牌底部拿一张牌放到牌顶
     * 2. 从桌子上拿一张牌放到牌顶
     */
    public static LinkedList<Integer> findCardLocation(int[] location){
        // 如果桌子上没牌
        if(location.length == 0){
            return new LinkedList<>();
        }
        LinkedList<Integer> result = new LinkedList<>(); // 答案
        for (int i = location.length - 1; i >=0; i--) {
            if (result.size() > 0){
                result.addFirst(result.removeLast());// 从牌底部拿一张到牌顶
            }
            result.addFirst(location[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(CardMove.findCardLocation(new int[]{1,3,2}));
    }
}

