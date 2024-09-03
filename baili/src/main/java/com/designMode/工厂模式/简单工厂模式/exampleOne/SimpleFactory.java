package com.designMode.工厂模式.简单工厂模式.exampleOne;

// 定义工厂类
public class SimpleFactory {
    public static Product getProduct(String type) {
        Product product = null;
        if (ProductEnum.PRODUCT_A.getValue().equals(type)) {
            product = new ConcreteProductA();
        } else if (ProductEnum.PRODUCT_B.getValue().equals(type)) {
            product = new ConcreteProductB();
        }
        return product;
    }
}
