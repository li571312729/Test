package com.algorithm;

import java.util.ArrayList;
import java.util.List;

public class 二进制手表 {
    public static void main(String[] args) {
        List<String> strings = readBinaryWatch(3);
        System.out.println(strings);
    }

    /**
     * 1    2   4   8
     * 1    2   4   8   16  32
     * 输出的顺序没有要求。
     * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
     * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
     * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
     *
     * 输入: n = 1
     * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     *
     * @param num
     * @return
     */
    public static List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            for (int j = 0; j < 60; j++){
                if(Integer.bitCount(i) + Integer.bitCount(j) == num){
                    StringBuilder s = new StringBuilder();
                    s.append(i).append(":");
                    if(j < 10){
                        s.append("0");
                    }
                    s.append(j);
                    result.add(s.toString());
                }
            }
        }
        return result;
    }
}
