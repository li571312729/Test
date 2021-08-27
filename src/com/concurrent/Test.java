package com.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * @author lxq
 * @date 2021年08月17日 14:15
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(a1.a);
    }


}

class a1{

    public static final int a = 1;

    static {
        System.out.println("a1");
    }

}