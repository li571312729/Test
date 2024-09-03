package com.angorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lxq
 * @date 2021年07月19日 15:23
 */
public class 两个链表生成相加链表 {

    public static void main(String[] args) {
        ListNode a = new ListNode(9);
        ListNode b = new ListNode(9);
        ListNode c = new ListNode(9);
        a.next = b;
        b.next = c;
        ListNode d = new ListNode(9);
        ListNode e = new ListNode(9);
        d.next = e;
        ListNode listNode = addTwoNumbers(a, d);
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
         * 输入：l1 = [2,4,3], l2 = [5,6,4]
         * 输出：[7,0,8]
         * 解释：342 + 465 = 807.
     * @author lxq
     * @date 2021/7/19 15:24
     * @param l1
     * @param l2
     * @return com.solution.ListNode
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode data = null;
        ListNode before = null;
        int current = 0, carry = 0;

        while (l1 != null && l2 != null){
            current = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            if (data == null) {
                data = new ListNode(current);
                before = data;
            }else {
                before.next = new ListNode(current);
                before = before.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null){
            if(carry > 0){
                current = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
                before.next = new ListNode(current);
            }else {
                before.next = new ListNode(l1.val);
            }
            l1 = l1.next;
            before = before.next;
        }

        while (l2 != null){
            if(carry > 0){
                current = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
                before.next = new ListNode(current);
            }else {
                before.next = new ListNode(l2.val);
            }
            l2 = l2.next;
            before = before.next;
        }

        if(carry > 0){
            before.next = new ListNode(carry);
        }
        return data;
    }
}
