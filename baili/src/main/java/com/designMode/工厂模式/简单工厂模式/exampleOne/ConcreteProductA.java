package com.designMode.工厂模式.简单工厂模式.exampleOne;

// 实现产品接口的实体类
public class ConcreteProductA implements Product {
    @Override
    public void show() {
        System.out.println("具体产品A");
    }
}
