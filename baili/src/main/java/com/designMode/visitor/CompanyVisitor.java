package com.designMode.visitor;

public class CompanyVisitor implements Visitor {
    
    private int totalPrice;

    @Override
    public void buyCpu(Cpu cpu) {
        totalPrice += cpu.getPrice() * 0.7;
    }

    @Override
    public void buyMemery(Memory mem) {
        totalPrice += mem.getPrice() * 0.6;
    }

    @Override
    public void buyBoard(Board boa) {
        totalPrice += boa.getPrice() * 0.6;
    }
    
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
