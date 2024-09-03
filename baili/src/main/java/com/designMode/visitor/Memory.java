package com.designMode.visitor;

public class Memory extends ComputerPart {

    @Override
    public void accept(Visitor v) {
        v.buyMemery(this);
    }

    @Override
    public int getPrice() {
        return 800;
    }

}
