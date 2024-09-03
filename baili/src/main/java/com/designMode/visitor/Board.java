package com.designMode.visitor;

public class Board extends ComputerPart {

    @Override
    public void accept(Visitor v) {
        v.buyBoard(this);
    }

    @Override
    public int getPrice() {
        return 500;
    }

}
