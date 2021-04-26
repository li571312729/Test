package com.baili.test;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlowMeasure {

    public static void main(String[] args) {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 12.10));
        pairArrayList.add(new Pair<>("version", 12.19));
        pairArrayList.add(new Pair<>("version", 6.28));
// 生成的map集合中只有一个键值对：{version=6.28}
        Map<String, Double> collect = pairArrayList.stream().collect(
                Collectors.toMap(Pair::getKey, Pair::getValue, (k, v) -> v));
        System.out.println(collect);
    }
   
    
}
