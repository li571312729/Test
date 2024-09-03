package com.baili.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


public class StartSpectrum_recordTest {

    public static void main(String[] args) {
        String path = "F:\\yoodenWork\\robotSensor自检等插件\\spectrum_record\\spectrum.exe";
        String name = "spectrum.exe";
        while(true){
            if(!processRunningCheck(name)){
                System.out.println(LocalDateTime.now() + "--音频软件未启动!");
                //StartSpectrum_record(path);
            }else{
                System.out.println(LocalDateTime.now() + "--音频软件已启动!");
            }
            StartSpectrum_record(path);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
    
    private static void StartSpectrum_record(String audioSoftwarePath){
        try {
            Runtime.getRuntime().exec(audioSoftwarePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * 检测进程是否启动，参数:进程映像名称
     * return true/false  true:启动
     * */
    private static boolean processRunningCheck(String processName) {
        BufferedReader bufferedReader = null;
        try {
            Process proc = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq " + processName + "\"");
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(processName)) // 判断是否存在
                {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            return false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                }
            }
        }
    }
}
