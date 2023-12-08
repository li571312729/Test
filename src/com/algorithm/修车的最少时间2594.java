package com.algorithm;

/**
 * 给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranksi 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n2 分钟内修好 n 辆车。
 *
 * 同时给你一个整数 cars ，表示总共需要修理的汽车数目。
 *
 * 请你返回修理所有汽车 最少 需要多少时间。
 *
 * 注意：所有机械工可以同时修理汽车。
 */
public class 修车的最少时间2594 {

    public static void main(String[] args) {
        System.out.println(repairCars(new int[]{1}, 1));
    }

    /**
     * 使用二分的方式来做，先随便选一个时间，以这个时间为准，算一下这个时间内所有人
     * 能修的车辆数是否超过总的，如果超过则将时间砍一半，不能的话则增加一倍时间
     * @param ranks
     * @param cars
     * @return
     */
    public static long repairCars(int[] ranks, int cars) {
        //第一步就使用第一个工人修车一半车辆的时间
        long t0 = 0;
        long t1 = Long.MAX_VALUE;
        long t = (t0 + ranks[0] * cars * cars) / 2;
        t = t == 0 ? 1 : t;

        //第二步使用二分方式找到最小的那个能修完所有车辆的时间
        while (t0 < t1){
            long count = 0;
            for (int rank : ranks) {
                count += Math.sqrt(t / rank);
            }

            if (count >= cars) {
                t1 = t;
                t = (t0 + t) / 2;
            }else {
                t0 = t;
                if(t1 == Long.MAX_VALUE){
                    t = t * 2;
                }else {
                    t = (t1 + t) / 2;
                }
            }

            if(t1 - t0 == 1){
                break;
            }
        }
        return t1;
    }
}
