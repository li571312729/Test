package com.factory;

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
