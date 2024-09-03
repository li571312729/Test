package com.designMode.工厂模式.抽象工厂.exampleOne;

// 定义现代工厂
public class ModernFactory extends AbstractFactory {

    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new Ak47();
    }

    @Override
    Food createFood() {
        return new Bread();
    }

}
