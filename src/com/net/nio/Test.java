package com.net.nio;

import java.util.*;

/**
 * @author lxq
 * @date 2021年04月16日 13:35
 */
public class Test {

    public static void main(String[] args) {

        while (true){
            int v = new Random().nextInt(11);
            System.out.println(v);
            if(v == 10){
                break;
            }

        }
    }
}

