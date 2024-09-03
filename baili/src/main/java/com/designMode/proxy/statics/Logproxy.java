package com.designMode.proxy.statics;

public class Logproxy implements MoveAble {

    private MoveAble moveAble;
    
    public Logproxy(MoveAble moveAble) {
        this.setMoveAble(moveAble);
    }

    @Override
    public void run() {
        System.out.println("Logproxy.run()" + "----> Statr Move");
        moveAble.run();
        System.out.println("Logproxy.run()" + "----> End Move");
    }

    public MoveAble getMoveAble() {
        return moveAble;
    }

    public void setMoveAble(MoveAble moveAble) {
        this.moveAble = moveAble;
    }

}
