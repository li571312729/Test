package com.designMode.composite;

public class LeafNode extends Node {

    private String name;
    
    public LeafNode(String name) {
        super();
        this.name = name;
    }

    @Override
    void print() {
        System.out.println(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
