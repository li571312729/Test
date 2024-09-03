package com.angorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 最低加油次数871 {

    /**
     * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
     *
     * 沿途有加油站，用数组 stations 表示。其中 stations[i] = [positioni, fueli] 表示第 i 个加油站位于出发位置东面 positioni 英里处，并且有 fueli 升汽油。
     *
     * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
     *
     * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
     *
     * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
     *
     * stations = [[10,100]] 表示有一个加油站距离10英里，有100升汽油
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(minRefuelStops(1000000, 70768, new int[][]{{12575,171159},{81909,101253},{163732,164401},{190025,65493},{442889,31147},{481202,166081},{586028,206379},{591952,52748},{595013,9163},{611883,217156}}));
        System.out.println(minRefuelStops1(1000000, 70768, new int[][]{{12575,171159},{81909,101253},{163732,164401},{190025,65493},{442889,31147},{481202,166081},{586028,206379},{591952,52748},{595013,9163},{611883,217156}}));
    }

    /**
     * 动态规划
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    public static int minRefuelStops1(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        long[] dp = new long[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (dp[j] >= stations[i][0]) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }



    /**
     * 贪心解法 (根据加油站的属性，存油量和距离来算)
     *  - 在当前能到达的最远距离加油站加油
     *  - 在当前能到达的加油站中找最多油的加油站
     *  - 在当前能到达的加油站中寻找最合适的加油站，存油/距离
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        // 加油的次数
        int count = 0;
        int tempStartFuel = startFuel;

        // 起始油量足够，不需要加油
        if(tempStartFuel >= target){
            return count;
        }

        // 起始油量不足且无法抵达第一个加油站
        if(stations.length == 0 || tempStartFuel < stations[0][0]){
            return -1;
        }

        //已经走过的距离
        int length = 0;

        // {10, 60},{20, 30},{30, 30},{60, 40}
        // - 在当前能到达的最远距离加油站加油
        for (int i = 0; i < stations.length; i++) {
            if(tempStartFuel > (stations[i][0] - length) && i < stations.length - 1){
                continue;
            }

            //当前选择的加油站点
            int current = i - 1;
            if(tempStartFuel == (stations[i][0] - length) || i == stations.length - 1){
                current = i;
            }else {
                // 这里i--表示当前没到的这个加油站，加完油后要重新判断到没到
                i--;
            }

            // 上次加完油后到不了下一个加油站
            if(tempStartFuel < (stations[current][0] - length)){
                count = -1;
                break;
            }

            tempStartFuel = (tempStartFuel - (stations[current][0] - length)) + stations[current][1];
            length = stations[current][0];
            count ++;

            // 如果加完油后能够直达目的地则直接退出循环
            if((target - length) <= tempStartFuel){
                break;
            }else if(current == stations.length - 1){
                //如果是最后一个站点的话，加完油还是不能到终点，则设置加油次数为-1，表示这种方案到不了
                count = -1;
            }
        }

        // - 在当前能到达的加油站中找最多油的加油站  {10, 60},{20, 30},{30, 30},{60, 40}
        //保存每次循环最多油的那个下标
        int index = -1;
        int count1 = 0;
        tempStartFuel = startFuel;
        length = 0;
        for (int i = 0; i < stations.length; i++){
            if(tempStartFuel >= (stations[i][0] - length)){
                if(index == -1){
                    index = i;
                }else {
                    index = stations[i][1] >= stations[index][1] ? i : index;
                }

                if(i < stations.length - 1){
                    continue;
                }
            }

            //上次加完油后到不了下一个加油站了，所以直接中断
            if(index == -1){
                count1 = -1;
                break;
            }

            tempStartFuel = (tempStartFuel - (stations[index][0] - length)) + stations[index][1];
            count1 ++;
            i = index;
            length = stations[index][0];
            index = -1;

            // 如果加完油后能够直达目的地则直接退出循环
            if((target - length) <= tempStartFuel){
                break;
            }else if(i == stations.length - 1){
                //如果是最后一个站点的话，加完油还是不能到终点，则设置加油次数为-1，表示这种方案到不了
                count1 = -1;
            }
        }

        //- 在当前能到达的加油站中寻找最合适的加油站，能加的油/走到这消耗的距离 {10, 60},{20, 30},{30, 30},{60, 40}
        index = -1;
        double ratio = 0d;
        int count2 = 0;
        tempStartFuel = startFuel;
        length = 0;
        for (int i = 0; i < stations.length; i++){
            if(tempStartFuel >= (stations[i][0] - length)){
                if(index == -1){
                    index = i;
                    ratio = (double) stations[i][1] / (double)(stations[i][0]);
                }else {
                    double tRatio = (double)stations[i][1] / (double)(stations[i][0]);
                    if(tRatio - ratio > -0.000001 || Math.abs(tRatio - ratio) < 0.000001){
                        index = i;
                        ratio = tRatio;
                    }
                }

                if(i < stations.length - 1){
                    continue;
                }
            }

            //上次加完油后到不了下一个加油站了，所以直接中断
            if(index == -1){
                count2 = -1;
                break;
            }

            tempStartFuel = (tempStartFuel - (stations[index][0] - length)) + stations[index][1];
            count2 ++;
            i = index;
            length = stations[index][0];
            index = -1;
            ratio = 0d;

            // 如果加完油后能够直达目的地则直接退出循环
            if((target - length) <= tempStartFuel){
                break;
            }else if(i == stations.length - 1){
                //如果是最后一个站点的话，加完油还是不能到终点，则设置加油次数为-1，表示这种方案到不了
                count2 = -1;
            }
        }


        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        if(count > -1){
            queue.add(count);
        }
        if(count1 > -1){
            queue.add(count1);
        }
        if(count2 > -1){
            queue.add(count2);
        }
        return queue.isEmpty() ? -1 : queue.poll();
    }
}
