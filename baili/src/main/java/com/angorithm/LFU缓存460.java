package com.angorithm;

import java.util.*;

/**
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *
 * 实现 LFUCache 类：
 *
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，
 * 则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 *
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class LFU缓存460 {

    //记录每个使用计数区间内的所有key
    Map<Integer, Deque<Integer>> cntFreq;
    //记录每个元素的使用计数
    Map<Integer, DataNode> data;
    int capacity;

    public LFU缓存460(int capacity) {
        this.cntFreq = new HashMap<>();
        this.data = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        DataNode dataNode = data.get(key);
        if(dataNode != null){
            changeCntFreq(dataNode.cnt, dataNode.cnt + 1, key);
            dataNode.cnt = dataNode.cnt + 1;
            return dataNode.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(data.size() < capacity || data.containsKey(key)){
           if(!data.containsKey(key)){
               data.put(key, new DataNode());
           }
            DataNode dataNode = data.get(key);
            dataNode.value = value;
            changeCntFreq(dataNode.cnt, dataNode.cnt + 1, key);
            dataNode.cnt = dataNode.cnt + 1;
        }else {
            int minCnt = cntFreq.keySet().stream().min(Comparator.naturalOrder()).get();
            int reKey = cntFreq.get(minCnt).pollFirst();
            if(cntFreq.get(minCnt).isEmpty()){
                cntFreq.remove(minCnt);
            }

            data.remove(reKey);
            DataNode dataNode = new DataNode();
            dataNode.value = value;
            dataNode.cnt = 1;
            data.put(key, dataNode);
            if(!cntFreq.containsKey(dataNode.cnt)){
                cntFreq.put(dataNode.cnt, new LinkedList<>());
            }
            cntFreq.get(dataNode.cnt).addLast(key);
        }
    }

    public void changeCntFreq(int originCnt, int newCnt, int key){
        if(cntFreq.containsKey(originCnt)){
            cntFreq.get(originCnt).remove(key);
            if(cntFreq.get(originCnt).isEmpty()){
                cntFreq.remove(originCnt);
            }
        }

        if(!cntFreq.containsKey(newCnt)){
            cntFreq.put(newCnt, new LinkedList<>());
        }
        cntFreq.get(newCnt).addLast(key);
    }

    class DataNode{
        int value;
        int cnt;
    }

    public static void main(String[] args) {
        LFU缓存460 test = new LFU缓存460(2);
        test.put(1, 1);
        test.put(2, 2);
        System.out.println(test.get(1));
        test.put(3, 3);
        System.out.println(test.get(2));
        System.out.println(test.get(3));
        test.put(4, 4);
        System.out.println(test.get(1));
        System.out.println(test.get(3));
        System.out.println(test.get(4));
    }


}
