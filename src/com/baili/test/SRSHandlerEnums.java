package com.baili.test;

/**
 * @author lxq
 * @date 2020/10/9 14:30
 */
public enum SRSHandlerEnums {
    ON_CONNECT("on_connect"),
    ON_CLOSE("on_close"),
    ON_PUBLISH("on_publish"),
    ON_UNPUBLISH("on_unpublish"),
    ON_PLAY("on_play"),
    ON_STOP("on_stop"),
    ON_DVR("on_dvr");

    private String value;

    SRSHandlerEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
