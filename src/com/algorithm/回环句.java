package com.algorithm;

public class 回环句 {

    public static void main(String[] args) {
        String a = "leetcode exercises sound delightful";
        System.out.println(isCircularSentence(a));
    }

    public static boolean isCircularSentence(String sentence) {
        String[] array = sentence.split(" ");

        char a = '0';
        for (int i = 0; i < array.length; i++) {
            if(i == array.length - 1){
                if(array[i].charAt(array[i].length() - 1) != array[0].charAt(0)){
                    return false;
                }
            }

            if(i > 0){
                if(array[i].charAt(0) != array[i - 1].charAt(array[i - 1].length() - 1)){
                    return false;
                }
            }
        }
        return true;
    }
}
