package com.solution;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lxq
 * @date 2021年06月02日 15:05
 */
public class 两数之和 {

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{120, 45, 60, 87, 50, 80}, 167);
        Arrays.stream(ints).forEach(System.out::println);
    }

    /**
     *
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public static int[] twoSum (int[] numbers, int target) {
        if(numbers == null || numbers.length == 0){
            return new int[]{};
        }

        int[] result = new int[2];
        // write code here
        Map<Integer, Integer> map = new HashMap<>(16);

        for (int i = 0; i < numbers.length; i++) {
            int data = target - numbers[i];
            if(map.get(data) != null ){
                result[0] = map.get(data) + 1;
                result[1] = i + 1;
                break;
            }
            map.put(numbers[i], i);
        }
        return result;
    }
}

