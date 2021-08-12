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
        int[] ints = twoSum(new int[]{2,7,11,15}, 9);
        int[] ints1 = twoSum1(new int[]{2,7,11,15}, 9);
        Arrays.stream(ints).forEach(System.out::println);
        Arrays.stream(ints1).forEach(System.out::println);
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
                result[0] = map.get(data);
                result[1] = i;
                break;
            }
            map.put(numbers[i], i);
        }
        return result;
    }

    /**
     *
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public static int[] twoSum1 (int[] numbers, int target) {
        if(numbers == null || numbers.length == 0){
            return new int[]{};
        }

        int[] result = new int[2];
        int max = Integer.MIN_VALUE;
        for (int number : numbers) {
            max = Math.max(max, number);
        }

        int[] temp = new int[max];
        Arrays.fill(temp, -1);

        for (int i = 0; i < numbers.length; i++) {
            int data = target - numbers[i];
            if (data < 0) {
                continue;
            }

            if(temp[data] != -1 ){
                result[0] = temp[data];
                result[1] = i;
                break;
            }
            temp[numbers[i]] = 0;
        }
        return result;
    }
}

