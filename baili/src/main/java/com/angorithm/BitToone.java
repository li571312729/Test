package com.angorithm;

/**
 * 二进制减到1的步骤
 * @author xiaoqiangli
 * @Date 2022-02-22
 */
public class BitToone {


    public static void main(String[] args) {
        System.out.println(numSteps("100101011"));
    }

    /**
     *         1         1
     *         2        10
     *         3        11
     *         4       100
     *         5       101
     *         6       110
     *         7       111
     *         8      1000
     *         9      100101011  14
     *                10001  1001 2  101 2  11 2  1 3
     *
     * @param s
     * @return
     */
    public static int numSteps(String s) {
       // 解题思路，这个题的规律是 我们从后往前找。。如果后面是0，则直接去掉然后次数+1即可
       // 如果后面是1，第一步需要+1然他成为10，然后第二部去除这个0，所以 尾部有k个1，则需要 k+1次操作，
       // 比如1011 尾部两个1需要 3（一次+1变成 1100，2次删除0的操作） 次操作 变成11

        int n = 0;
        int count = 0;
        int l = s.length() - 1;

        while(l >= 0){
            // 末尾的0
            if(s.charAt(l) == '0' && count == 0){
                n++;
            }else if(s.charAt(l) == '0' && count > 0){
                // 后面有1,当前这个0认为是1
               n += count + 1;
               count = 1;
            }else if(s.charAt(l) == '1' && (count > 0 || l != 0)){
                // 只有从后面加上来的1才算，，如果像 100000这样的。第一个1是不算的我们只需要把0去除完即可满足条件
                count ++;
            }
            l--;
        }

        if(count > 0){
            n += count + 1;
        }
        return n;
    }
}
