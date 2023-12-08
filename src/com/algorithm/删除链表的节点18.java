package com.algorithm;


/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 */
public class 删除链表的节点18 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        System.out.println(deleteNode(head, 3));
    }

    public static ListNode deleteNode(ListNode head, int val) {
        if(head == null){
            return head;
        }

        ListNode temp = head;
        ListNode pre = null;
        while (temp!= null){
            if(temp.val == val){
                //删除的是头节点
                if(pre == null){
                    return temp.next;
                }

                //删除的不是头节点
                pre.next = temp.next;
                break;
            }
            pre = temp;
            temp = temp.next;
        }
        return head;
    }
}
