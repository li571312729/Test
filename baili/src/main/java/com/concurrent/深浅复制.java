package com.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 深浅复制 {

    private String name;

    /**
     * new ArrayList<>(list);这种方式是浅复制
     * @param args
     */
    public static void main(String[] args) {
        test();

        深浅复制 s = new 深浅复制();
        s.setName("hello");

        List<深浅复制> list = new ArrayList<>();
        list.add(s);

        List<深浅复制> a1 = new ArrayList<>(list);
        List<深浅复制> a2 = new ArrayList<>(list);

        for (深浅复制 temp : a1) {
            temp.setName("one " + temp.getName());
        }

        System.out.println(a1.get(0).getName());

        for (深浅复制 temp : a2) {
            temp.setName("two " + temp.getName());
        }

        System.out.println(a2.get(0).getName());
    }

    public static void test(){
        深浅复制[] a = new 深浅复制[1];
        深浅复制 s = new 深浅复制();
        s.setName("hello");
        a[0] = s;

        //深浅复制[] b = new 深浅复制[1];

       // System.arraycopy(a, 0, b, 0, 1);
        深浅复制[] b = Arrays.copyOf(a, 1);


        System.out.println(b[0].getName());
        b[0].setName("11111");

        System.out.println(b[0].getName());
        System.out.println(a[0].getName());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
