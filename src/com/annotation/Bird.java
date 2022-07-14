package com.annotation;

/**
 * @author xiaoqiangli
 * @Date 2022-06-22
 */
public interface Bird {

    static void print() {
        System.out.println("C语言中文网");
    }

    default void show() {
        System.out.println("我正在学习C语言中文网Java教程");
    }

    void fly();
}
