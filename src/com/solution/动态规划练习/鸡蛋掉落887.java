package com.solution.动态规划练习;

import com.algorithm.Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoqiangli
 * @Date 2022-01-14
 */
public class 鸡蛋掉落887 {

    public static void main(String[] args) {
        System.out.println(superEggDrop(2, 9));
    }


    /**
     *
     给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。

     已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。

     每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。

     请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     * @param k
     * @param n
     * @return
     */
    public static int superEggDrop(int k, int n) {
        return dp(k, n);
    }

    static Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public static int dp(int k, int n) {
        if (!memo.containsKey(n * 100 + k)) {
            int ans;
            if (n == 0) {
                ans = 0;
            } else if (k == 1) {
                ans = n;
            } else {
                int lo = 1, hi = n;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(k - 1, x - 1);
                    int t2 = dp(k, n - x);

                    if (t1 < t2) {
                        lo = x;
                    } else if (t1 > t2) {
                        hi = x;
                    } else {
                        lo = hi = x;
                    }
                }

                ans = 1 + Math.min(Math.max(dp(k - 1, lo - 1), dp(k, n - lo)), Math.max(dp(k - 1, hi - 1), dp(k, n - hi)));
            }

            memo.put(n * 100 + k, ans);
        }
        return memo.get(n * 100 + k);
    }

    private static void print(int[][] dp){
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
