package com.algorithm;

public class Z字形变换 {

    public static void main(String[] args) {
        System.out.println(convert("AB", 1));
    }


    /**
     * 0     6      12        18   取余 2n-2 == 0
     * 1   5 7   11 13    17        == 1/5
     * 2 4   8 10   14 16           == 2/4
     * 3     9      15              == 3
     *
     * 根据这个找规则，大于行数 - 1的直接用2n - 2相减即可 等于行数的取余就为0
     *
     * @param s
     * @param n
     * @return
     */
    public static String convert(String s, int n) {
        if(n == 1){
            return s;
        }
        StringBuffer[] reAr = new StringBuffer[n];
        for (int i = 0; i < n; i++) {
            reAr[i] = new StringBuffer();
        }

        char[] chars = s.toCharArray();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < chars.length; i ++ ) {
            /*
                i % (n + n -2) == 0
                第一行的元素间隔 是 行数加上中间(n-2 行数减头尾行)的 就是 n + (n - 2)
             */
            int resu = i % (n + n - 2);
            if(resu == 0){
                reAr[0].append(chars[i]);
            }

            if(resu > 0 && resu <= n - 1 ){
                reAr[resu].append(chars[i]);
            }

            if(resu > n - 1){
                reAr[2 * n - 2 - resu].append(chars[i]);
            }
        }

        for (StringBuffer ss : reAr) {
            result.append(ss);
        }

        return result.toString();
    }

}
