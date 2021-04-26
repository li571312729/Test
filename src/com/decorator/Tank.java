package com.decorator;

public class Tank extends GameObject{
    
    public Tank(String name) {
        super();
        this.name = name;
    }

    @Override
    void print() {
        System.out.println(name + " kachakacha...");   
    }

}
