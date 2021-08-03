package com.baili.test;

/**
 * @author lxq
 * @date 2020/10/9 14:30
 */
public enum SRSHandlerEnums {
    onConnect("on_connect", "连接动作"),
    onClose("on_close", "关闭连接动作"),
    onPublish("on_publish", "开始推流"),
    onUnPublish("on_unpublish", "停止推流"),
    onPlay("on_play", "开始播放"),
    onStop("on_stop", "停止播放"),
    onDvr("on_dvr", "当DVR录制，关闭一个flv文件时"),
    onHls("on_hls", "hls"),
    onHlsNotify("on_hls_notify", "hls");

    /**
     * action
     */
    private String action;

    private String desc;

    SRSHandlerEnums(String action, String desc) {
        this.action = action;
        this.desc = desc;
    }

    public String getAction() {
        return action;
    }

    public String getDesc() {
        return desc;
    }
}
