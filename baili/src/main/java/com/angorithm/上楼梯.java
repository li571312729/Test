package com.angorithm;

public class 上楼梯 {

    public static void main(String[] args) {
        上楼梯 上楼梯 = new 上楼梯();
        System.out.println(climbStairs(5));
    }

    /**
     * 1:1
     * 2:2
     * 3:3
     * 4:5
     * 5:8
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if(n <= 2){
            return n;
        }

        int i1 = 1;
        int i2 = 2;
        int temp = 0;
        for(int i = 3; i <= n; i++){
            temp = i1 + i2;
            i1 = i2;
            i2 = temp;
        }
        return temp;
    }


}
