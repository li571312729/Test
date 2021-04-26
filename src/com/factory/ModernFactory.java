package com.factory;

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
