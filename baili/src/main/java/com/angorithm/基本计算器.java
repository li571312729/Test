package com.angorithm;

import java.util.Stack;

/**
 * @author xiaoqiangli
 * @Date 2021-12-06
 */
public class 基本计算器 {

    public static void main(String[] args) {

        System.out.println(calculate("2147483647"));
    }

    /**
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * @param s s 由数字、'+'、'-'、'('、')'、和 ' ' 组成,并且s是一个有效的表达式
     * @return
     */
    public static int calculate(String s) {
        // 解题思路 使用栈来实现，遇到右括号时 进行短暂的计算到上个左括号的中间的和值，然后再将之入栈，，持续这个步骤到最后一个元素，
        // 然后循环结束后 将栈里剩余的进行计算即可。 On的时间 和 On的空间

        int data = 0;
        Stack<String> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (char c : chars) {

            switch (c){
                // (1+(4+5+2)-3)+(6+8)
                case 40: // '('
                    stack.push("(");
                    break;
                case 41: // ')'
                    String t;
                    String previous = null;
                    int tem = 0;
                    while (!(t = stack.pop()).equals("(")){
                        if (t.equals("-")) {
                            tem -= (Integer.parseInt(previous) * 2);
                        }else if (!t.equals("+")){
                            previous = t;
                            tem += Integer.parseInt(previous + "");
                        }
                    }
                    stack.push(tem + "");
                    break;
                case 43:
                    if(stack.size() == 0){
                        stack.push("0");
                    }
                    stack.push("+");
                    break;
                case 45:
                    if(stack.size() == 0){
                        stack.push("0");
                    }
                    stack.push("-");
                    break;
                case 32: // ' '
                    break;
                default:
                    if(!stack.empty()){
                        String temp = stack.peek();
                        if(temp.equals("+") || temp.equals("-") || temp.equals("(")){
                            stack.push(c+"");
                        }else {
                            stack.push(Integer.parseInt(stack.pop()) * 10 + (c - '0') + "");
                        }
                    }else {
                        stack.push(c+"");
                    }
                    break;
            }
        }

        String t;
        String previous = null;
        while (!stack.empty()){
            t = stack.pop();
            if (t.equals("-")) {
                data -= (Integer.parseInt(previous) * 2);
            }else if (!t.equals("+")){
                previous = t;
                data += Integer.parseInt(previous + "");
            }
        }
        return data;
    }
}
