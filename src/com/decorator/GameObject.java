package com.decorator;

public abstract class GameObject {
    
    protected String name;
    
    abstract void print();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
