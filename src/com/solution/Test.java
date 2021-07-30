package com.solution;

/**
 * @author lxq
 * @date 2021年07月28日 17:40
 */
public class Test {

    public static void main(String[] args) {
        int a = 31;
        a = (a & 0x55555555) + ((a >> 1) & 0x55555555);
        a = (a & 0x33333333) + ((a >> 2) & 0x33333333);
        a = (a & 0x0f0f0f0f) + ((a >> 4) & 0x0f0f0f0f);
        a = (a * 0x01010101) >>24;

        System.out.println(a);

    }

}
