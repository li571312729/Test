package com.angorithm;

import java.util.Arrays;

public class FBNQ {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(fbnq(21 * 55)));
    }

    private static int[] fbnq(int value) {
        int i = 1;
        int[] result = new int[3];

        while (true){
            int cu = fbnqDg(i);
            int beCu = fbnqDg(i - 1);
            if( cu * beCu == value){
                result[0] = i - 1;
                result[1] = i;
                result[2] = 1;
                break;
            }else if(cu * beCu < value){
                i++;
            }else {
                result[0] = i - 1;
                result[1] = i;
                result[2] = 0;
                break;
            }
        }
        return result;
    }

    private static int fbnqDg(int i) {
        if(i == 0 || i == 1){
            return 1;
        }
        return fbnqDg(i - 1) + fbnqDg(i - 2);
    }


}
