package com.solution;

public class 求正整数平方根 {

    public static void main(String[] args) {
        double v = MySqrt1(2);
        System.out.println(v);
    }


    static double MySqrt(int value){

        double left = 0;
        double right = value;
        double mid = (right + left) / 2;

        double offset = 0.001 ;

        while ((right -left) > offset){
            double temp = mid*mid;
            if (temp > value){
                right = mid;
            }

            if (temp <= value){
                left = mid;
            }

            mid = (left + right) / 2;
        }

        return mid;

    }

    static double MySqrt1(int value){

        double a = value;

        while ((a*a - value) > 0.001){
            a = (a + value/a) / 2;
            System.out.println(a);
        }
        return a;
    }
}
