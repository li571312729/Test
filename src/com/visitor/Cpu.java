package com.visitor;

public class Cpu extends ComputerPart {

    @Override
    public void accept(Visitor v) {
        v.buyCpu(this);
    }
    
    @Override
    public int getPrice() {
        return 1000;
    }

}
