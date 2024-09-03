package com.angorithm;

/**
 * n 个人站成一排，按从 1 到 n 编号。
 *
 * 最初，排在队首的第一个人拿着一个枕头。每秒钟，拿着枕头的人会将枕头传递给队伍中的下一个人。一旦枕头到达队首或队尾，传递方向就会改变，队伍会继续沿相反方向传递枕头。
 *
 * 例如，当枕头到达第 n 个人时，TA 会将枕头传递给第 n - 1 个人，然后传递给第 n - 2 个人，依此类推。
 * 给你两个正整数 n 和 time ，返回 time 秒后拿着枕头的人的编号
 *
 * 输入：n = 4, time = 5
 * 输出：2
 * 解释：队伍中枕头的传递情况为：1 -> 2 -> 3 -> 4 -> 3 -> 2 。
 * 5 秒后，枕头传递到第 2 个人手中。
 */
public class 递枕头2582 {

    public static void main(String[] args) {
        System.out.println(passThePillow(4, 8));
    }

    public static int passThePillow(int n, int time) {
        //结果编号
        int number = 1;

        //正常传一遍的时间
        int endTime = n - 1;

        //传递次数，如果为奇数说明此时是从后往前的，偶数则是从前往后传递中
        int count = time / endTime;

        //剩余的传递次数
        int residue = time % endTime;

        if(count % 2 == 0){
            number = 1 + residue;
        }else {
            number = n - residue;
        }
        return number;
    }
}
