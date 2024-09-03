package com.designMode.工厂模式.工厂方法模式.exampleOne;

/**
 * 工厂方法模式是一种常用的设计模式，它将对象的创建延迟到子类中进行，使得父类工厂不需要知道具体的创建过程
 */
public class Client {

    public static void main(String[] args) {
        // 创建对应的子工厂
        // 狗狗
        AnimalFactory dogFactory = new DogFactory();
        Animal dog = dogFactory.createAnimal();
        dog.speak(); //输出：汪汪汪

        // 创建对应的子工厂
        // 猫猫
        AnimalFactory carFactory = new CatFactory();
        Animal cat = carFactory.createAnimal();
        cat.speak();  //输出：喵喵喵
    }

}
