package com.designMode.工厂模式.简单工厂模式.exampleOne;

/**
 * 客户端代码
 * 简单工厂模式，会在工厂里面直接创建子类
 */
public class Client {
    public static void main(String[] args) {
        Product productA = SimpleFactory.getProduct("A");
        productA.show();

        Product productB = SimpleFactory.getProduct("B");
        productB.show();
    }
}
