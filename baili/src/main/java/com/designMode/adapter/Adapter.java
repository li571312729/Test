package com.designMode.adapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Adapter {
    public static void main(String[] args){
        try (
            FileInputStream fir = new FileInputStream("e:/test.txt");
            //BufferedReader按行读取接收的是Reder，所以这里InputStreamReader相当于适配器把FileInputStream转换成Reder传给BufferedReader
            InputStreamReader ir = new InputStreamReader(fir);
            BufferedReader bReader = new BufferedReader(ir);
        ){
            String line = bReader.readLine();
            while(line != null){
                System.out.println(line);
                line = bReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}