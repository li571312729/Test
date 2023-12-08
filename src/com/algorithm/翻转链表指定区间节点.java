package com.algorithm;

/**
 * 翻转链表中指定区间的节点
 * @author 86158
 */
public class 翻转链表指定区间节点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(algorithmTest(head, 3, 5));

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        System.out.println(algorithmTest1(head1, 3, 5));
    }

    /**
     * 以1->2->3->4->5, m = 2, n=4 为例:
     *
     * - 定位到要反转部分的头节点 2，head = 2；前驱结点 1，pre = 1；
     * - 当前节点的下一个节点3调整为前驱节点的下一个节点 1->3->2->4->5,
     * - 当前结点仍为2， 前驱结点依然是1，重复上一步操作。。。
     * - 1->4->3->2->5.
     * @param head
     * @param m
     * @param n
     * @return
     */
    private static ListNode algorithmTest1(ListNode head, int m, int n) {
        if(head == null){
            return head;
        }

        int count = 1;
        ListNode prev = null;
        //第一步找到翻转的前一个节点
        while (count < m){
            count ++;
            if(prev != null){
                prev = prev.next;
            }else {
                prev = head;
            }

            if(prev.next == null){
                break;
            }
        }

        //开始翻转
        //链表长度小于翻转初始起点
        if(count < m){
            return head;
        }

        // 1  (2)  3   4   5
        ListNode first = prev.next;

        while (m < n && first != null && first.next != null){
            ListNode temp = prev.next;
            ListNode mid = first.next;
            ListNode end = mid.next;
            prev.next = mid;
            first.next = end;
            mid.next = temp;
            m ++;
        }
        return head;
    }

    /**
     * 笨方法，循环找到翻转起点，然后一个一个翻转
     * @param head
     * @param m
     * @param n
     * @return
     */
    private static ListNode algorithmTest(ListNode head, int m, int n) {
        if(head == null){
            return head;
        }

        int count = 1;
        ListNode prev = null;
        //第一步找到翻转的前一个节点
        while (count < m){
            count ++;
            if(prev != null){
                prev = prev.next;
            }else {
                prev = head;
            }

            if(prev.next == null){
                break;
            }
        }

        //开始翻转
        //链表长度小于翻转初始起点
        if(count < m){
            return head;
        }

        // 1  (2)  3   4   5   6   7   8
        ListNode first = prev.next;
        prev.next = null;

        ListNode temp = first;
        ListNode mid = temp.next;
        temp.next = null;
        ListNode end = null;

        while (temp != null && m < n && mid != null){
            end = mid.next;
            mid.next = temp;
            temp = mid;
            mid = end;
            m ++;
        }

        //重新链接链表
        prev.next = temp;
        if(first != null){
            first.next = mid;
        }
        return head;
    }
}