package com.designMode.工厂模式.抽象工厂.exampleOne;

//定义魔法工厂
public class MargicFactory extends AbstractFactory {

    @Override
    Vehicle createVehicle() {
        return new Broom();
    }

    @Override
    Weapon createWeapon() {
        return new Margic();
    }

    @Override
    Food createFood() {
        return new Mushroom();
    }

}
