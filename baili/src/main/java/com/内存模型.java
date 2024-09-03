package com;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class 内存模型 {

    private static List<String> r = new LinkedList<>();

    public static void main(String[] args) {
        A o = new A();
        int[] b = new int[3];
        b[0] = 1;
        b[1] = 2;
        b[2] = 3;
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
    }
}

class  A {
   private int num;
   private long count;
   private A a;
}