package com.solution;

import java.util.Stack;

/**
 * @author lxq
 * @date 2021年06月11日 15:48
 */
public class 用两个栈实现队列 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     * @author lxq
     * @date 2021/6/11 15:49
     * @param args
     */
    public static void main(String[] args) {
        用两个栈实现队列 a = new 用两个栈实现队列();
        a.push(1);
        a.push(2);
        a.push(3);
        a.push(4);
        System.out.println(a.pop());
        System.out.println(a.pop());
        System.out.println(a.pop());
        System.out.println(a.pop());
        a.push(5);
        a.push(6);
        System.out.println(a.pop());
        System.out.println(a.pop());
    }

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

}
