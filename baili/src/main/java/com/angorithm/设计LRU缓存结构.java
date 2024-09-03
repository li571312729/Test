package com.angorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author lxq
 * @date 2021年06月10日 10:36
 */
public class 设计LRU缓存结构 {

    public static void main(String[] args) {
        设计LRU缓存结构 a = new 设计LRU缓存结构();
        for (int i : a.LRU(new int[][]{{2, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}}, 3)) {
            System.out.println(i);
        }
    }


    /**
     * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
     * set(key, value)：将记录(key, value)插入该结构
     * get(key)：返回key对应的value值
     * [要求]
     * set和get方法的时间复杂度为O(1)
     * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
     * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
     * 若opt=1，接下来两个整数x, y，表示set(x, y)
     * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
     * 对于每个操作2，输出一个答案
     *
     * [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
     *
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        LinkedHashMap<Integer, Integer> lruM = new LinkedHashMap<>(k);

        ArrayList<Integer> resTemp = new ArrayList<>(operators.length);
        for(int[] op: operators){
            switch(op[0]){
                case 1:
                    if(lruM.size() >= k){
                        // 超过了则移除链头的元素，因为最先进的始终放在头部，
                        Iterator dd = lruM.keySet().iterator();
                        lruM.remove(dd.next());
                        lruM.put(op[1],op[2]);
                    }else{
                        lruM.put(op[1],op[2]);
                    }
                    break;
                case 2:
                    if(lruM.containsKey(op[1])){
                        // 改元素进行了get操作，则将之置为最常使用元素（移除老位置，将之重新加入链表尾）
                        int temp = lruM.get(op[1]);
                        lruM.remove(op[1]);
                        lruM.put(op[1], temp);
                        resTemp.add(temp);
                    }else{
                        resTemp.add(-1);
                    }
                    break;
                default:;
            }
        }


        int[] resArray = resTemp.stream().mapToInt(o -> o).toArray();
        return resArray;
    }
}

