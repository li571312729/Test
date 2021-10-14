package com.net.nio;

import org.apache.poi.ss.formula.functions.T;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @date 2021年04月16日 13:35
 */
public class Test {

    public void test(){
        while (true){
            System.out.println(11111111);
        }
    }

    public static void main(final String... args) throws InterruptedException {
        Test t = new Test();
        t.test();
    }

}

