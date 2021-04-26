package com.visitor;

public class PersonalVisitor implements Visitor {
    
    private int totalPrice;

    @Override
    public void buyCpu(Cpu cpu) {
        totalPrice += cpu.getPrice() * 0.9;
    }

    @Override
    public void buyMemery(Memory mem) {
        totalPrice += mem.getPrice() * 0.8;
    }

    @Override
    public void buyBoard(Board boa) {
        totalPrice += boa.getPrice() * 0.7;
    }
    
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
