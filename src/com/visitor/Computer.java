package com.visitor;

public class Computer {
    
    ComputerPart cpu = new Cpu();
    ComputerPart memory = new Memory();
    ComputerPart board = new Board();
    
    public void accept(Visitor v){
        cpu.accept(v);
        memory.accept(v);
        board.accept(v);
    }
    
}
