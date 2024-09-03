package com.angorithm;

public class NC126换钱的最少货币数 {

    public static void main(String[] args) {
        System.out.println(minMoney(new int[]{5,2,3}, 3));
    }


    /**
     * 最少货币数
     * @param arr int整型一维数组 the array
     * @param aim int整型 the target
     * @return int整型
     */
    public static int minMoney (int[] arr, int aim) {
        //定一个全局最大值
        int Max = aim + 1;

        //dp[i]的含义是目标值为i的时候最少钱币数是多少。
        int []dp = new int[aim + 1];

        // 这里将dp初始化为max是因为根据递推公式选择较小的，因此初值设置为大值
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Max;
        }

        //总金额为0的时候所需钱币数一定是0
        dp[0] = 0;

        // 遍历目标值
        for(int i = 1;i <= aim;i ++){
            // 遍历钱币
            for(int j = 0;j < arr.length;j ++){
                //如果当前的钱币比目标值小就可以兑换
                if(arr[j] <= i){
                    // dp[i-arr[j]] + 1 表示，如果我想用这张纸币进行兑换，则我必须拥有目标值减去该纸币对应的剩下的兑换方案。
                    // 如果有的话，那就说明这张纸币可以用来兑换，则新的数目就等于 （目标值减去该纸币对应的剩下的兑换方案 + 1（1 就是这张纸币））
                    dp[i] = Math.min(dp[i],dp[i-arr[j]] + 1);
                }
            }
        }
        return dp[aim] > aim ? -1 : dp[aim];
    }
}
