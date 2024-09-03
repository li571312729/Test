package com.designMode.工厂模式.抽象工厂.exampleOne;

// 定义抽象工厂
public abstract class AbstractFactory {
    abstract Vehicle createVehicle();
    abstract Weapon createWeapon();
    abstract Food createFood();
}
