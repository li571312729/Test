package com.designMode.flyweight;

import java.util.UUID;

public class Car {

    private UUID id;
    private String name;
    private String model;
    private boolean useFlag;

    public Car(String name, String model) {
        super();
        this.name = name;
        this.model = model;
        this.setId(UUID.randomUUID());
        this.useFlag = true;
    }
    
    public void run(){
        System.out.println(name + " runrunrun......");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isUseFlag() {
        return useFlag;
    }

    public void setUseFlag(boolean useFlag) {
        this.useFlag = useFlag;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", name=" + name + ", model=" + model + "]";
    }
}
