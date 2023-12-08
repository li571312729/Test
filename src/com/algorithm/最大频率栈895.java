package com.algorithm;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author 86158
 */
public class 最大频率栈895 {

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }


}

class FreqStack {

    //记录每个元素的频率
    Map<Integer, Integer> freq;
    //记录每个频率中的元素
    Map<Integer, Deque<Integer>> groups;
    //记录当前的最大频率
    int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        groups = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        freq.put(val, freq.getOrDefault(val, 0) + 1);
        groups.putIfAbsent(freq.get(val), new LinkedList<>());
        groups.get(freq.get(val)).addFirst(val);
        maxFreq = Math.max(maxFreq, freq.get(val));
    }

    public int pop() {
        int val = groups.get(maxFreq).poll();
        freq.put(val, freq.get(val) - 1);
        if (freq.get(val) == 0) {
            freq.remove(val);
        }

        if(groups.get(maxFreq).isEmpty()){
            maxFreq --;
        }
        return val;
    }
}