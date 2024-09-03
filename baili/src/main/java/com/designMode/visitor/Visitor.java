package com.designMode.visitor;

public interface Visitor {
    public void buyCpu(Cpu cpu);
    public void buyMemery(Memory mem);
    public void buyBoard(Board boa);
}
