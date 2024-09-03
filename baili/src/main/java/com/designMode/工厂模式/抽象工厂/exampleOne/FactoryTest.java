package com.designMode.工厂模式.抽象工厂.exampleOne;

/**
 * 抽象工厂模式在工厂方法模式的基础上 聚合了一系列相关的或依赖的对象。
 * 这种模式非常适合于一组相关产品对象的创建，比如现代工厂和魔法工厂能够生产出不同系列的汽车、武器和食物。
 */
public class FactoryTest {

    public static void main(String[] args) {
        AbstractFactory af = new ModernFactory();
        AbstractFactory am = new MargicFactory();


        Vehicle car = af.createVehicle();
        car.go();
        Weapon ak47 = af.createWeapon();
        ak47.shoot();
        Food bread = af.createFood();
        bread.description();
        
        Vehicle broom = am.createVehicle();
        broom.go();
        Weapon margic = am.createWeapon();
        margic.shoot();
        Food mushroom = am.createFood();
        mushroom.description();
    }

}
