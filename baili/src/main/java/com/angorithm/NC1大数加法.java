package com.angorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lxq
 * @date 2021年07月22日 10:13
 */
public class NC1大数加法 {

    public static void main(String[] args) {
        System.out.println(solve("1", "99"));
    }

    /**
     * 描述
     * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
     * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
     * @author lxq
     * @date 2021/7/22 10:14
     * @param s
     * @param t
     * @return java.lang.String
     */
    public static String solve (String s, String t) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();

        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();

        for (int i = s1.length - 1, j = t1.length - 1; i >= 0 || j >= 0; i--, j--) {
            if (i >= 0) {
                stack1.offer((int)s1[i] - 48);
            }else {
                stack1.offer(0);
            }

            if (j >= 0) {
                stack2.offer((int)t1[j] - 48);
            }else {
                stack2.offer(0);
            }
        }

        int data = 0;
        int carry = 0;
        StringBuffer result = new StringBuffer();
        while (stack1.size() > 0){
            data = stack1.poll() + stack2.poll() + carry;
            carry = data / 10;
            result.insert(0, data % 10);
        }

        if(carry > 0){
            result.insert(0, carry);
        }
        
        return result.toString();
    }
}
