package com.strategy;

import java.util.Arrays;

public class StrategyTest {

    public static void main(String[] args) {
        Sort<Dog> sort = new Sort<>();
        Dog[] arr = new Dog[]{new Dog(1, 1), new Dog(2, 2), new Dog(13, 13), new Dog(5, 5)};
        sort.sort(arr, (o1, o2) -> {
            if(o1.getHeight() > o2.getHeight()) return 1;
            if(o1.getHeight() < o2.getHeight()) return -1;
            return 0;
        });
        System.out.println(Arrays.toString(arr));
    }

}
