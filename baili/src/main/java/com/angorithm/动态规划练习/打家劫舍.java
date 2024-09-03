package com.angorithm.动态规划练习;

public class 打家劫舍 {

    public static void main(String[] args) {
        int data = getMoneyOneRow(new int[]{1, 2, 4, 3, 2, 5, 6});
        int data1 = getMoneyCircle(new int[]{1, 2, 4, 3, 2, 5, 6});
        System.out.println(data);
        System.out.println(data1);
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * 这个地方所有的房屋没有围成一圈，而是排成一列
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * 举例【2， 3， 2】
     * data = 4
     * @param ints
     * @return
     *
     */
    private static int getMoneyOneRow(int[] ints) {
        int length = ints.length;

        // dp[i] 表示从从第一间偷到第i间屋子的最高金额
        int[] dp = new int[length + 1];

        /**
         * 计算dp[i]的时候有2种情况：
         *      1.第i间房屋不偷 则当前价值就是 dp[i - 1]的最大值
         *      2.第i间房屋偷，那就是dp[i- 2] + ints[i]的值
         */
        dp[1] = ints[0];

        for (int i = 2; i <= length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i -2] + ints[i - 1]);
        }

        return dp[length];
    }


    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * 这个地方所有的房屋都围成了一圈
     * 这意味着你只能在头尾房间中选择一间
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * 举例【2， 3， 2】
     * data = 3
     * 第一间和第三间连在一起，因此这里选择第2间最合适
     * @param ints
     * @return
     */
    private static int getMoneyCircle(int[] ints) {
        if(ints == null || ints.length == 0){
            return 0;
        }

        if(ints.length <= 1){
            return ints[0];
        }

        int length = ints.length;

        // 既然头尾只能选择一间，那我们可以将环拆成两部分，
        // 一部分是从第一间到倒数第二间，意思是不选最后一间
        // 另一部分是从第二间到倒数第一间，意思是不选第一间
        // 这样就把环拆成两个单项列，按照第一种做法解题即可

        // dp[i] 表示从从第一间偷到第i间屋子的最高金额， dp 存储的是 第一间到倒数第二间
        // df 存储的是从第二间到倒数第一间

        int[] dp = new int[length];
        int[] df = new int[length];

        /**
         * 计算dp[i]的时候有2种情况：
         *      1.第i间房屋不偷 则当前价值就是 dp[i - 1]的最大值
         *      2.第i间房屋偷，那就是dp[i- 2] + ints[i]的值
         */
        dp[1] = ints[0];
        df[1] = ints[1];

        // dp 不偷最后一间因此 i <= length - 1把最后一间去掉
        for (int i = 2; i <= length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i -2] + ints[i - 1]);
        }
        //【2， 3， 2, 3, 5, 4】

        for (int i = 3; i <= length; i++) {
            df[i - 1] = Math.max(df[i - 2], df[i -3] + ints[i - 1]);
        }

        return Math.max(dp[length - 1], df[length - 1]);
    }

}
