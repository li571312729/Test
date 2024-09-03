package com.angorithm;

/**
 * @author xiaoqiangli
 * @Date 2021-11-26
 */
public class 相交链表 {

    public static void main(String[] args) {
        ListNode headA = new ListNode(3);
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(3);

        ListNode headB = new ListNode(4);
        ListNode f = new ListNode(1);
        ListNode g = new ListNode(8);
        ListNode h = new ListNode(4);
        ListNode k = new ListNode(5);

        ListNode c = new ListNode(4);
        ListNode d = new ListNode(5);
        ListNode e = new ListNode(6);

        headB.next = f;
        f.next = g;
        g.next = h;
        h.next = k;

        ListNode intersectionNode = getIntersectionNode(headA, headB);
        System.out.println(intersectionNode);
    }


    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     *
     *      0   0   0
     *                   8   4   5
     *  0   0   4   1
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
