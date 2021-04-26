package com.factory;

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
