package com.solution;

import java.math.BigInteger;
import java.util.*;

/**
 * @author lxq
 * @date 2021年07月19日 15:23
 */
public class 两个链表生成相加链表 {

    public static void main(String[] args) {
        ListNode a = new ListNode(9);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(7);
        a.next = b;
        b.next = c;
        ListNode d = new ListNode(6);
        ListNode e = new ListNode(5);
        d.next = e;
        ListNode listNode = addInList1(a, d);
        System.out.println(11111111);
    }

    /**
     * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
     * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
     * 例如：
     *      链表 1 为 9->3->7，链表 2 为 6->3，
     *      最后生成新的结果链表为 1->0->0->0。
     * @author lxq
     * @date 2021/7/19 15:24
     * @param head1
     * @param head2
     * @return com.solution.ListNode
     */
    public static ListNode addInList1 (ListNode head1, ListNode head2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        ListNode tempNode1 = head1;
        ListNode tempNode2 = head2;

        while (tempNode1 != null || tempNode2 != null){
            if (tempNode1 !=null ) {
                stack1.push(tempNode1.val);
                tempNode1 = tempNode1.next;
            }

            if (tempNode2 != null){
                stack2.push(tempNode2.val);
                tempNode2 = tempNode2.next;
            }
        }

        ListNode header = null;
        int data = 0;
        int carry = 0;
        while (stack1.size() > 0 || stack2.size() > 0) {
            data = (stack1.size() > 0 ? stack1.poll() : 0) + (stack2.size() > 0 ? stack2.poll() : 0) + carry;
            carry = data / 10;

            ListNode current = new ListNode(data % 10);
            current.next = header;
            header = current;
        }

        if(carry > 0){
            ListNode current = new ListNode(carry);
            current.next = header;
            header = current;
        }
        return header;
    }

    /**
     * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
     * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
     * 例如：
     *      链表 1 为 9->3->7，链表 2 为 6->3，
     *      最后生成新的结果链表为 1->0->0->0。
     * @author lxq
     * @date 2021/7/19 15:24
     * @param head1
     * @param head2
     * @return com.solution.ListNode
     */
    public static ListNode addInList (ListNode head1, ListNode head2) {
        ListNode tempNode1 = head1;
        ListNode tempNode2 = head2;
        StringBuffer s1 = new StringBuffer("");
        StringBuffer s2 = new StringBuffer("");

        while (tempNode1 != null || tempNode2 != null){
            if (tempNode1 !=null ) {
                if(!("".equals(s1) && tempNode1.val == 0)){
                    s1.append(tempNode1.val);
                }
                tempNode1 = tempNode1.next;
            }

            if (tempNode2 != null){
                if(!("".equals(s2) && tempNode2.val == 0)){
                    s2.append(tempNode2.val);
                }
                tempNode2 = tempNode2.next;
            }
        }
        BigInteger result = new BigInteger("".equals(s1.toString()) ? "0" : s1.toString()).add(new BigInteger("".equals(s2.toString()) ? "0" : s2.toString()));
        int i;
        System.out.println(result);

        ListNode header = null;
        do {
            i = result.mod(new BigInteger("10")).intValue();
            result = result.divide(new BigInteger("10"));
            ListNode current = new ListNode(i);
            current.next = header;
            header = current;
        }while (result.compareTo(new BigInteger("0")) > 0);
        return header;
    }
}
