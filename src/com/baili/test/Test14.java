package com.baili.test;

/**
 * @author lxq
 * @date 2021年04月22日 13:59
 */
public class Test14 {
    public static void main(String[] args) {
        String a = "rtmp://x/x?token=asbdjbakjdsbf2&aa=110";

        String s = a.substring(a.indexOf("token"));
        String s1 = s.split("&")[0].split("=")[1];
        System.out.println(s1);


    }
}

