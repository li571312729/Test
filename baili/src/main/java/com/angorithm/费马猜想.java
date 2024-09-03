package com.angorithm;

/**
 * @author Administrator
 */
public class 费马猜想 {

    public static void main(String[] args) {
        feiMa(0);
    }

    public static boolean feiMa(double n){
        double result;
        do {
            n ++;
            result = Math.pow(2, Math.pow(2, n)) + 1;
            System.out.println("n = " + n + ", result = " + result);
        }while (isPrimeNormal(result));
        return false;
    }

    /**
     * 判断是否为素数/质数的常规方法
     * 判断n是否为素数，根据定义直接判断从2到n-1是否存在n的约数即可
     * @param num
     * @return
     */
    public static boolean isPrimeNormal(double num) {
        for(int i = 2; i < num; i++) {
            if(num % i == 0) {
                System.out.println("找到的约数是: " + i);
                return false;
            }
        }
        return true;
    }

}
