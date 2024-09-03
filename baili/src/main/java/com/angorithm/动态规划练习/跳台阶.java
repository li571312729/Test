package com.angorithm.动态规划练习;

/**
 * @author lxq
 * @date 2021年06月10日 11:00
 */
public class 跳台阶 {

    public static void main(String[] args) {
        跳台阶 a = new 跳台阶();
        int i = a.jumpFloor(5);
        int j = a.jumpFloor1(5);
        System.out.println(i);
        System.out.println(j);
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * 必须要知道的是 跳第n层的跳法 = (n-1)  + (n-2)
     * 第一种递归
     * @author lxq
     * @date 2021/6/10 11:01
     * @param target
     * @return int
     */
    public int jumpFloor(int target) {
        if(target < 0){
            return 0;
        }

        if(target == 1){
            return 1;
        }

        if(target == 2){
            return  2;
        }
        return jumpFloor(target -1) + jumpFloor(target - 2);
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * 必须要知道的是 跳第n层的跳法 = (n-1)  + (n-2)
     * 第二种非递归
     * @author lxq
     * @date 2021/6/10 11:01
     * @param target
     * @return int
     */
    public int jumpFloor1(int target) {
        if(target < 0){
            return 0;
        }

        int fir = 1;
        int sec = 2;

        if (target < 3) {
            return target;
        }

        int sum = 0;
        for (int i = 3; i <= target; i++) {
            sum = fir + sec;
            fir = sec;
            sec = sum;
        }
        return sum;
    }
}

