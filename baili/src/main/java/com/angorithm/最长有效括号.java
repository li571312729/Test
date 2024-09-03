package com.angorithm;



import java.util.Stack;

/**
 * 给你一个只包含 `'('` 和 `')'` 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 */
public class 最长有效括号 {

    public static void main(String[] args) {
        System.out.println(maxLengthEffectiveBrackets(")()((())"));
    }


    /**
     * 解题思路：1.
     *
     * - 用栈模拟一遍，将所有无法匹配的括号的位置全部置1
     * - 例如: "()(()"的mark为[0, 0, 1, 0, 0]
     * - 再例如: " "的mark为[1, 0, 0, 1, 0, 0, 0, 0]
     * - 经过这样的处理后, 此题就变成了寻找最长的连续的0的长度
     *
     * 栈模拟的时候因为只有左右括号，直接入栈元素下标即可
     * @param s
     * @return
     */
    private static int maxLengthEffectiveBrackets(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int[] ar = new int[chars.length];
        Character top = null;

        //用栈模拟一遍，将所有无法匹配的括号的位置全部置1
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            }

            if(chars[i] == ')'){
                if(stack.isEmpty() || chars[stack.pop()] != '('){
                    ar[i] = 1;
                }
            }
        }
        //栈非空时处理剩余的元素
        while (!stack.isEmpty()){
            ar[stack.pop()] = 1;
        }

        //寻找最长的连续的0的长度
        int max = 0;
        int count = 0;
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == 0) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        return Math.max(max, count);
    }


}
