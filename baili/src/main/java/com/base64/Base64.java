package com.base64;

import java.util.ArrayList;

public class Base64 {

    private static final char[] base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public static void main(String[] args) {
        String a = "hello";
        System.out.println(toBase64String(a));
    }

    public static String toBase64String(String str) {
        StringBuilder result = new StringBuilder();

        StringBuilder binaryData = new StringBuilder();
        for (char c : str.toCharArray()) {
            String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binaryData.append(binaryChar);
        }
        System.out.println(binaryData.toString());

        int startIndex = 0;
        ArrayList<Integer> strList = new ArrayList();
        for (int i = 0; i < binaryData.length(); i++) {
            if (i != 0 && i % 6 == 0) {
                String temp = "00" + binaryData.substring(startIndex, i);
                startIndex = i;
                strList.add(Integer.parseInt(temp, 2));
            }
        }
        if (startIndex < binaryData.length()) {
            String temp = "00" + binaryData.substring(startIndex);
            int addZero = 6 - binaryData.length() % 6;  //计算出后续补0的个数
            for (int i = 0; i < addZero; i++) {
                temp = temp + "0";
            }
            strList.add(Integer.parseInt(temp, 2));
        }

        strList.forEach(o -> {
//            result.append(convert(o));
            result.append(base64Chars[o % 64]);
        });

        int addEqual = 4 - strList.size() % 4;
        if (addEqual > 0) {
            for (int i = 0; i < addEqual; i++) {
                result.append('=');
            }
        }
        return result.toString();
    }


    /**
     * int转base64码，使用asc码和base64码表的映射完成
     *
     * @param w
     * @return
     */
    private static char convert(int w) {
        if (w >= 0 & w <= 25) {
            return (char) (w + 65);
        } else if (w >= 26 && w <= 51) {
            return (char) (w + 71);
        } else if (w >= 52 & w <= 61) {
            return (char) (w - 4);
        } else if (w == 62) {
            return '+';
        } else if (w == 63) {
            return '/';
        }
        throw new RuntimeException("convert error");
    }
}
