package com.baili.test;

import org.openjdk.jol.info.ClassLayout;

public class ClassLayoutTest {

    public static void main(String[] args) {
       //Object o = new Object();
       Test test = new Test();
       synchronized (test) {
           System.out.println(ClassLayout.parseInstance(test).toPrintable());
       }
    } 
    
}

class Test{
      private byte a ;
//      private int c;
//    private double b ;
  
}