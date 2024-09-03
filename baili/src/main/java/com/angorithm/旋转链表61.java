package com.angorithm;

import java.util.Objects;

/**
 * @author xiaoqiangli
 * @Date 2021-12-29
 */
public class 旋转链表61 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(4);
        ListNode head4 = new ListNode(5);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        ListNode listNode = rotateRight(head, 4);
        System.out.println(listNode.val);
    }

    /**
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     *
     * 输入：head = [1,2,3,4,5], k = 1
     * 输出：[5,1,2,3, 4]
     *
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if(Objects.isNull(head)){
            return head;
        }
        int length = 1;

        ListNode temp = head;
        ListNode interval = null;

        // 第一遍循环 确定链表长度，以及倒数k + 1个元素（前提是k不能超过链表长度）
        while (temp.next != null){
            temp = temp.next;
            if(++length > k){
                if(Objects.nonNull(interval)){
                    interval = interval.next;
                }else {
                    interval = head;
                }
            }
        }

        int dif = k % length;   // 想象成一个圆环，，取余为0，相当于转回原位

        if(dif == 0){
            return head;
        }

        // 如果k比length大则，根据取余的数继续找到倒数 dif + 1的地方
        if(k > length){
            temp = head;
            while (temp.next != null){
                temp = temp.next;
                if(--dif <= 0){
                    if(Objects.nonNull(interval)){
                        interval = interval.next;
                    }else {
                        interval = head;
                    }
                }
            }
        }
        temp.next = head;
        ListNode data = interval.next;
        interval.next = null;

        return data;
    }
}
