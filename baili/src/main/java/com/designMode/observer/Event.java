package com.designMode.observer;

public class Event<T> {
    
    private String Msg;
    private T t;
    
    public Event(String msg, T t) {
        Msg = msg;
        this.t = t;
    }

    public T getResource(){
        return t;
    };

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

}
