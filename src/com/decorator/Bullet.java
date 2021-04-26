package com.decorator;

public class Bullet extends GameObject {
    
    public Bullet(String name) {
        super();
        this.name = name;
    }

    @Override
    void print() {
        System.out.println(name + " dadadada...");   
    }

}
