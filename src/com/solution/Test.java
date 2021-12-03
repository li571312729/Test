package com.solution;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiaoqiangli
 * @Date 2021-11-25
 */
public class Test {

    public static void main(String[] args) throws IOException {
        //Properties prop1 = new Properties();
        //prop1.load(Test.class.getClassLoader().getResourceAsStream("a.properties"));
        //System.out.println(prop1.getProperty("a"));

        int intValue = Math.round(2.552f);
        System.out.println(intValue);
    }

    public static void terst(HashMap<String, String> map){
        map.forEach((k,v) -> {
            System.out.println("k:" + k + ",    v:" + v);
        });
    }
}
