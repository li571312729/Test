package com.solution;

import java.util.Objects;

/**
 * @author xiaoqiangli
 * @Date 2021-11-23
 */
public class 删除排序链表中的重复元素 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode e = new ListNode(3);

        head.next = b;
        b.next = e;

        ListNode listNode = deleteDuplicates(head);
        System.out.println(11111);
    }

    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，
     * 请你删除所有重复的元素，使每个元素 只出现一次 。
     * 返回同样按升序排列的结果链表
     *
     * 1 -> 1 -> 1 -> 2 -> 2 -> 3 -> 3
     * 123
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if(Objects.isNull(head) || Objects.isNull(head.next)){
            return head;
        }

        ListNode current = head;
        ListNode next = current.next;

        while (Objects.nonNull(next)){
            if(next.val == current.val){
                do{
                    next = next.next;
                } while (Objects.nonNull(next) && next.val == current.val);

                if(Objects.nonNull(next)){
                    current.next = next;
                    current = current.next;
                }else {
                    current.next = null;
                    break;
                }

            }else {
                current = next;
            }
            next = next.next;
        }

        return head;
    }


}
