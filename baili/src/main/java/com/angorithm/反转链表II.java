package com.angorithm;

import java.util.Objects;

/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 */
public class 反转链表II {

    public static void main(String[] args) {
        ListNode list = new ListNode(1)
                .buildNext(new ListNode(2)
                        .buildNext(new ListNode(3)
                                .buildNext(new ListNode(4)
                                        .buildNext(new ListNode(5)
                                                .buildNext(new ListNode(6)
                                                        .buildNext(new ListNode(7)
                                                                .buildNext(new ListNode(8)
                                                                        .buildNext(new ListNode(9)))))))));

        System.out.println(ConvertListNode(list, 2,3));
        String s = "abcda";
        int i = s.indexOf(55);
        System.out.println(1111);
    }

    /**
     *  实现思路 ：以1->2->3->4->5, m = 2, n=4 为例:
     * - 定位到要反转部分的头节点 2，head = 2；前驱结点 1，pre = 1；
     * - 当前节点的下一个节点3调整为前驱节点的下一个节点 1->3->2->4->5,
     * - 当前结点仍为2， 前驱结点依然是1，重复上一步操作。。。
     * - 1->4->3->2->5.
     * @param list
     * @param left
     * @param right
     */
    private static ListNode ConvertListNode(ListNode list, int left, int right) {
        if(Objects.isNull(list)){
            return null;
        }

        ListNode cur = list, dumy = new ListNode(-1).buildNext(list), prev = dumy;
        while (cur != null){
            if(cur.val != left){
                cur = cur.next;
                prev = prev.next;
                continue;
            }
            break;
        }

        if(Objects.isNull(cur)){
            return list;
        }

        //1->2->3->4->5
        ListNode temp;
        while (cur.next != null) {
            temp = cur.next;
            cur.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
            if(temp.val == right){
                break;
            }
        }

        return prev == dumy ? prev.next : dumy.next;
    }
}
