package com.designMode.责任链结合策略;

public abstract class Handler {
    private Handler next;
    protected void setNext(Handler handler){
        next = handler;
    }

    public Handler getNext(){
        return next;
    }

    public abstract boolean handleRequest(Object object);
}