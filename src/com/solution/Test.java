package com.solution;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaoqiangli
 * @Date 2021-11-25
 */
@Slf4j
public class Test {

    private List<String> a = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BigDecimal bigDecimal = new BigDecimal(85);
        BigDecimal multiply = bigDecimal.divide(new BigDecimal(80), 10, RoundingMode.HALF_UP);

        System.out.println(String.format("result:%s", multiply));
    }

    public static void terst(HashMap<String, String> map){
        map.forEach((k,v) -> {
            System.out.println("k:" + k + ",    v:" + v);
        });
    }
}
