package com.designMode.flyweight;

public class Main {
    
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Car car = CarPool.getCar();
            System.out.println(car);
            car.run();
            System.out.println();
            if(i == 3 || i == 5){
                CarPool.destroy(car);
            }
        }
    }

}
