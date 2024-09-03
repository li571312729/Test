package com.designMode.工厂模式.工厂方法模式.exampleOne;

//定义专门的子工厂类
public class CatFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}
