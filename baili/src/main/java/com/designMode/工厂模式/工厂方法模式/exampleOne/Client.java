package com.designMode.工厂模式.工厂方法模式.exampleOne;

import java.math.BigDecimal;

/**
 * 工厂方法模式是一种常用的设计模式，它将对象的创建延迟到子类中进行，使得父类工厂不需要知道具体的创建过程
 */
public class Client {

    public static void main(String[] args) {
//        int a = Integer.parseInt("110.0");
        Integer integer = new BigDecimal("110.0").intValue();
    }

}
