package com.solution;

/**
 * @author lxq
 * @date 2021年05月28日 16:52
 */
public class 翻转链表 {

    public static void main(String[] args) {
        翻转链表 a = new 翻转链表();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode2.next = listNode3;
        ListNode listNode = a.ReverseList(listNode1);
        System.out.println(11111111);
    }

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        boolean headFlag = true;
        ListNode previousNode = head;
        ListNode nextNode = head.next;

        while (nextNode != null){
            head = nextNode;
            nextNode = head.next;
            head.next = previousNode;
            if(headFlag){
                previousNode.next = null;
                headFlag = false;
            }
            previousNode = head;
        }
        return  previousNode;
    }

}

class ListNode {
    int val;
    ListNode next = null;

    public ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}