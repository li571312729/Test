package com.angorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 */
public class N字形变换6 {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public static String convert(String s, int numRows) {
        if(numRows < 2){
            return s;
        }
        char[] chars = s.toCharArray();
        List<StringBuilder> lines = new ArrayList<>(numRows);
        for(int i = 0; i < numRows; i++) {
            lines.add(new StringBuilder());
        }

        int line = -1;
        //line的循环方向是否为下
        boolean down = true;
        for (char c : chars) {
            if(down){
                line ++;
            }else {
                line --;
            }
            lines.get(line).append(c);

            //调整line方向
            if(line == numRows - 1){
                down = false;
            }
            if (line == 0) {
                down = true;
            }
        }

        return lines.stream().collect(Collectors.joining());
    }
}
