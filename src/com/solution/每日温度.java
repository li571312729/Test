package com.solution;

import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author xiaoqiangli
 * @Date 2021-12-21
 */
public class 每日温度 {

    public static void main(String[] args) {
        System.out.println("原数组：" + Arrays.toString(new int[]{73,74,75,71,69,72,76,73}));
        System.out.println("解数组：" + Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }

    /**
     * 请根据每日 气温列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     *
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        if(temperatures == null || temperatures.length == 0){
            return new int[]{};
        }

        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.empty() && temperatures[i] > temperatures[stack.peek()]){
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }

        while (!stack.empty()){
            result[stack.pop()] = 0;
        }
        return result;
    }
}
