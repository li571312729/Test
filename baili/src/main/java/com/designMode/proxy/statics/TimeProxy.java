package com.designMode.proxy.statics;

public class TimeProxy implements MoveAble {

    private MoveAble moveAble;
    
    public TimeProxy(MoveAble moveAble) {
        this.setMoveAble(moveAble);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        moveAble.run();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public MoveAble getMoveAble() {
        return moveAble;
    }

    public void setMoveAble(MoveAble moveAble) {
        this.moveAble = moveAble;
    }

}
