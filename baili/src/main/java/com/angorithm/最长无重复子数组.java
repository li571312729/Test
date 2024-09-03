package com.angorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lxq
 * @date 2021年06月21日 16:10
 */
public class 最长无重复子数组 {


    public static void main(String[] args) {
        int i = maxLength1(new int[]{1,3,9,8,7,6,5,4,3,2,1});
        System.out.println(i);
    }

    public static int maxLength1 (int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 1;
        for(int start = 0, end = 0; end<arr.length ; end++){
            if(map.containsKey(arr[end])){
                //重复了
                start = Math.max(start, map.get(arr[end])+1);
                //注意：这里一定要取最大的start，不然就错误了
                //为什么？ 因为重复数字的索引很可能比start小
            }
            max = Math.max(max , end-start+1);
            map.put(arr[end], end);
        }
        return max;
    }

    /**
     * 给定一个数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
     * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
     * @author lxq
     * @date 2021/6/21 16:23
     * @param arr
     * @return int
     */
    public static int maxLength (int[] arr) {
        if(arr == null || arr.length == 0){
            return 0;
        }

        // {1,3,9,8,7,6,5,4,3,2,1}

        int max = 0;
        int current = 0;
        // key 值，value 值对应的索引
        Map<Integer, Integer> map = new LinkedHashMap<>(16);

        for (int i = 0; i < arr.length; i++) {
            if(map.containsKey(arr[i])){
                max = Math.max(max, current);
                int previous = map.get(arr[i]);
                Iterator<Integer> iterator = map.keySet().iterator();
                while (iterator.hasNext()){
                    Integer next = iterator.next();
                    if(map.get(next) <= previous){
                        iterator.remove();
                        current --;
                    }else {
                      break;
                    }
                }
            }
            current ++;
            map.put(arr[i], i);
        }

        return Math.max(max, current);
    }

}
