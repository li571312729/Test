package com.solution;

/**
 * @author lxq
 * @date 2021年08月10日 17:07
 */
public class 数组中最长连续递增序列 {

    public static void main(String[] args) {
        print(LIS(new int[]{2,1,5,3,6,4,8,9,7}));
    }

    /**
     * 寻找一个数组里连续递增的子序列最大长度
     * @author lxq
     * @date 2021/8/10 17:07
     * @param arr
     * @return int
     */
    public static int LCSLength (int[] arr) {
        // dp[i] 表示从下标为i的位置触出发递增子序列最大长度
        int[] dp = new int[arr.length];

        // 初始化
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = i + 1; j <= dp.length - 1; j++) {
                if(arr[j] > arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
            print(dp);
        }
        return max;
    }

    /**
     * 45,40,50,30,35
     * @author lxq
     * @date 2021/8/11 14:03
     * @param arr
     * @return int[]
     */
    public static int[] LIS (int[] arr) {
        int n = arr.length;
        // dp[i] 表示从下标为i的位置触出发递增子序列最大长度
        int[] dp = new int[n];

        // 初始化
        int max = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j <= n - 1; j++) {
                if(arr[j] > arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
            print(dp);
        }

        int[] data = new int[max];
        int count = 0;
        for (int i = n - 1; i >= 0 && max > 0; i--) {
            if (dp[i] == max && arr[i] > data[count - 1 >= 0 ? count - 1 : 0]) {
                data[count ++] = arr[i];
                max --;
                i = n;
            }
        }
        return data;
    }

    public static void print(int[] dp){
        for (int anInt : dp) {
            System.out.print(anInt + "   ");
        }
        System.out.println();
    }
}
