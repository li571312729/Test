package com.angorithm;

/**
 * 给你一个整数 money ，表示你总共有的钱数（单位为美元）和另一个整数 children ，表示你要将钱分配给多少个儿童。
 *
 * 你需要按照如下规则分配：
 *
 * 所有的钱都必须被分配。
 * 每个儿童至少获得 1 美元。
 * 没有人获得 4 美元。
 * 请你按照上述规则分配金钱，并返回 最多 有多少个儿童获得 恰好 8 美元。如果没有任何分配方案，返回 -1 。
 *
 * 示例 1：
 *
 * 输入：money = 20, children = 3
 * 输出：1
 * 解释：
 * 最多获得 8 美元的儿童数为 1 。一种分配方案为：
 * - 给第一个儿童分配 8 美元。
 * - 给第二个儿童分配 9 美元。
 * - 给第三个儿童分配 3 美元。
 * 没有分配方案能让获得 8 美元的儿童数超过 1 。
 */
public class 将钱分给最多的儿童2591 {

    public static void main(String[] args) {
        System.out.println(distMoney(23, 2));
    }

    public static int distMoney(int money, int children) {
        if(money < children){
            return -1;
        }

        // 首先每人分配1美元
        money -= children;
        // 然后尝试再分配7美元,计算出能分配的人数
        int count = money / 7;
        //部分人获得8元，则计算剩余钱
        money -= count * 7;

        //如果钱很多，相当于每人都可以分配7元,还剩下一些钱，则需要有手拿8元的人多拿一些
        if (count > children || (count == children && money > 0)){
            return children - 1;
        }

        //正好每个人8元
        if (count == children){
            return count;
        }

        //剩余一个人且剩余钱为3，则需要将3元部分给另外一个手拿8元的人，因此拿8元的人数需要减去1
        if(children - count == 1 && money == 3){
            return count -1;
        }
        return count;
    }
}
