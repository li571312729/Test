package com.flyweight;

import java.util.ArrayList;
import java.util.List;

public class CarPool {
    
    private static List<Car> cars = new ArrayList<>();
    static{
        for (int i = 1; i <= 5; i++) {
            cars.add(new Car("car0" + i, "长城"));
        }
    }
    
    public static Car getCar(){
        for (Car car : cars) {
            if(car.isUseFlag()){
                car.setUseFlag(false);
                return car;
            }
        }
        return new Car("tempCar", "长城");
    }
    
    public static void destroy(Car car){
        car.setUseFlag(true);
    }
}
