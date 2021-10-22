package com.concurrent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * @author lxq
 * @date 2021年08月17日 14:15
 */
public class Test {

    static class Human{
        int a = 2;

        public Human() {
            this.a = 4;
            show();
        }
        public void show(){
            System.out.println(a);
        }
    }
    static class Man extends Human{
        int a = 5;

        public Man() {
            this.a = 10;
            show();
        }
        @Override
        public void show(){
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        System.out.println(man.a);
    }


}
