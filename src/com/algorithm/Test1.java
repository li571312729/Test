package com.algorithm;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Test1 {
    public static void main(String[] args) {
        String text = "利刃在手制裁八方!"; // 要转换为语音的文本

        // 创建 ActiveX 组件
        ActiveXComponent speak = new ActiveXComponent("Sapi.SpVoice");

        try {
            // 音量 0 - 100
            speak.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            speak.setProperty("Rate", new Variant(0));

            // 获取 Dispatch 对象
            Dispatch dispatch = speak.getObject();

            // 设置语音属性
            Dispatch.call(dispatch, "Speak", new Variant(text));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            speak.safeRelease();
        }
    }
}
