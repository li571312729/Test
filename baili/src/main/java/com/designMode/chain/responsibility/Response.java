package com.designMode.chain.responsibility;

public class Response {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response [msg=" + msg + "]";
    }
}
