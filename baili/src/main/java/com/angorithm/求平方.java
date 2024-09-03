package com.angorithm;


import java.math.BigDecimal;

public class 求平方 {

    public static void main(String[] args) {
        System.out.println(squrt(20));
    }

    private static double squrt(double x) {
        if (x < 0) {
            return 0;
        }

        BigDecimal bigDecimal = new BigDecimal(x);

        double result = x / 2;
        double value = 0.01d;
        while (Math.abs(result * result - x) > value){
            if (new BigDecimal(result * result).compareTo(bigDecimal) > 0) {
                result = result / 2;
            }else {
                result = (result + 2 * result) / 2;
            }
        }
        return result;
    }


}
