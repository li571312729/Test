package com.angorithm;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 */
public class 有效的括号 {

    public static void main(String[] args) {
        System.out.println(effectiveBrackets("({})((({{{}})))"));
    }

    /**
     * 使用栈结构，遇到所有类型的左括号便入栈，遇到右括号的就出栈对比，不能匹配则不满足，最后栈里面还有元素也不满足。
     * @param s
     * @return
     */
    private static boolean effectiveBrackets(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        Character pop = null;
        for (char c : chars) {
            switch (c){
                case '(' | '{':
                    stack.push(c);
                break;
                case ')' | '}':
                    if(stack.isEmpty()){
                        return false;
                    }

                    pop = stack.pop();
                    if((c == ')' && pop != '(') || (c == '}' && pop != '{')){
                        return false;
                    }
                    break;
                default:
                    break;

            }
        }

        return stack.isEmpty();
    }


}
