package com.visitor;

public abstract class ComputerPart {
    public abstract void accept(Visitor v);
    public abstract int getPrice();
    
}
