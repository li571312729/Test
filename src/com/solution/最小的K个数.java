package com.solution;

import java.util.*;

/**
 * @author lxq
 * @date 2021年05月31日 11:46
 */
public class 最小的K个数 {

    public static void main(String[] args) {
        最小的K个数 a = new 最小的K个数();
        int[] param = new int[]{1, 2, 3, 4, 5, 6, 8, 15, 21, 29};
        ArrayList<Integer> integers = a.GetLeastNumbers_Solution2(param, 5);
        integers.stream().forEach(System.out::println);
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>(10);
        if (input == null || k <= 0 || k > input.length) {
            return result;
        }

        Arrays.stream(input).sorted().limit(k).forEach(o -> {
            result.add(o);
        });
        return result;
    }

    /**
     * 堆排序
     *
     * @param input
     * @param k
     * @return java.util.ArrayList<java.lang.Integer>
     * @author lxq
     * @date 2021/5/31 13:51
     */
    public ArrayList<Integer> GetLeastNumbers_Solution1(int[] input, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (k == 0 || k > input.length) {
            return ret;
        }

        Queue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int val : input) {
            if (pq.size() < k) {
                pq.add(val);
            } else {
                if (val < pq.peek()) {
                    pq.poll();
                    pq.add(val);
                }
            }
        }

        while (!pq.isEmpty()) {
            ret.add(pq.peek());
            pq.poll();
        }
        return ret;
    }

    /**
     * 分治的思想。类似快排 使得排序后一边都比另一边小，而 数量等于k
     * @author lxq
     * @date 2021/5/31 14:41
     * @param input
     * @param k 
     * @return java.util.ArrayList<java.lang.Integer>
     */    
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        ArrayList<Integer> leastNumbers = new ArrayList<>();
        while(input == null || k <= 0 || k > input.length) {
            return leastNumbers;
        }

        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);
        while(index != k-1){
            if(index < k-1){
                start = index + 1;
                index = partition(input, start, end);
            }else{
                end = index - 1;
                index = partition(input, start, end);
            }
        }

        for(int i = 0; i < k; i++){
            leastNumbers.add(input[i]);
        }
        return leastNumbers;
    }

    private int partition(int[] arr, int start, int end){
        int pivotKey = arr[start];
        while(start < end){
            while(start < end && arr[end] >= pivotKey) {
                end --;
            }
            swap(arr, start, end);
            while(start < end && arr[start] <= pivotKey) {
                start ++;
            }
            swap(arr, start, end);
        }
        return start;
    }

    private void swap(int[] arr, int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

