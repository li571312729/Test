package com.mat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author Abfeathers
 * @date 2021/3/24
 * @Description MAT内存泄漏分析
 *
 */

public class ObjectsMAT {


    static class A {
        B b = new B();
    }

    static class B {
        C c = new C();
    }

    static class C {
        List<String> list = new ArrayList<>();
    }

    static class Demo1 {
        Demo2 Demo2;

        public void setValue(Demo2 value) {
            this.Demo2 = value;
        }
    }

    static class Demo2 {
        Demo1 Demo1;

        public void setValue(Demo1 value) {
            this.Demo1 = value;
        }
    }

    static class Holder {
        Demo1 demo1 = new Demo1();
        Demo2 demo2 = new Demo2();

        Holder() {
            demo1.setValue(demo2);
            demo2.setValue(demo1);
        }


        private boolean aBoolean = false;
        private char aChar = '\0';
        private short aShort = 1;
        private int anInt = 1;
        private long aLong = 1L;
        private float aFloat = 1.0F;
        private double aDouble = 1.0D;
        private Double aDouble_2 = 1.0D;
        private int[] ints = new int[2];
        private String string = "1234";
    }

    Runnable runnable = () -> {
        Map<String, A> map = new HashMap<>();

        IntStream.range(0, 100).forEach(i -> {
            byte[] bytes = new byte[1024 * 1024];
            String str = new String(bytes).replace('\0', (char) i);
            A a = new A();
            a.b.c.list.add(str);

            map.put(i + "", a);
        });

        Holder holder = new Holder();

        try {
            //sleep forever , retain the memory
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    void startAbfeathersThread() throws Exception {
        new Thread(runnable, "abfeathers-thread").start();
    }

    public static void main(String[] args) throws Exception {
        ObjectsMAT objectsMAT = new ObjectsMAT();
        objectsMAT.startAbfeathersThread();
    }
}

