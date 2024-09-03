package com.designMode.工厂模式.工厂方法模式.exampleOne;

public class DogFactory extends AnimalFactory{
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}
