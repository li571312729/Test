package com.jacob;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobTest {

    public static void main(String[] args) throws Exception {
        text("F:/a.mp4", "利刃在手,制裁八方! 你们能杀死我吗啊啊啊啊啊！！！！！", 100, 0);
    }


    /**
     * 文本转音频
     *
     * @param path   音频生成路径
     * @param text   文本内容
     * @param volume 音量大小 0 - 100
     * @param speed  语音朗读速度 -10 到 +10
     * @return 是否成功
     */
    public static boolean text(String path, String text, int volume, int speed) {
        try {
            // 调用dll朗读方法
            ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
            // 音量 0 - 100
            ax.setProperty("Volume", new Variant(volume));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(speed));
            // 输入的语言内容
            Dispatch dispatch = ax.getObject();

            System.out.println(text);

            // 本地执行朗读
            Dispatch.call(dispatch, "Speak", new Variant(text));

            //开始生成语音文件，构建文件流
            ax = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch sfFileStream = ax.getObject();
            //设置文件生成格式
            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch fileFormat = ax.getObject();

            // 设置音频流格式
            Dispatch.put(fileFormat, "Type", new Variant(22));
            // 设置文件输出流格式
            Dispatch.putRef(sfFileStream, "Format", fileFormat);
            // 调用输出文件流打开方法，创建一个音频文件
            Dispatch.call(sfFileStream, "Open", new Variant(path), new Variant(3), new Variant(true));
            // 设置声音对应输出流为输出文件对象
            Dispatch.putRef(dispatch, "AudioOutputStream", sfFileStream);
            // 设置音量
            Dispatch.put(dispatch, "Volume", new Variant(volume));
            // 设置速度
            Dispatch.put(dispatch, "Rate", new Variant(speed));
            // 执行朗读
            Dispatch.call(dispatch, "Speak", new Variant(text));
            // 关闭输出文件
            Dispatch.call(sfFileStream, "Close");
            Dispatch.putRef(dispatch, "AudioOutputStream", null);

            // 关闭资源
            sfFileStream.safeRelease();
            fileFormat.safeRelease();
            // 关闭朗读的操作
            dispatch.safeRelease();
            ax.safeRelease();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
