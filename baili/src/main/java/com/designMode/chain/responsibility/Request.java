package com.designMode.chain.responsibility;

public class Request {
    private String msg;

    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "Request [msg=" + msg + "]";
    }
}
