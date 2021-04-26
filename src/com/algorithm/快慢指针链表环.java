package com.algorithm;

public class 快慢指针链表环 {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = head;

        System.out.println(hasCycle(head));
        System.out.println(detectCycle(head));
    }


    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public static boolean hasCycle(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }

        ListNode flow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            if (flow == fast) {
                return true;
            }
            flow = flow.next;
            fast = fast.next.next;
        }
        return false;
    }


    public static ListNode detectCycle(ListNode head) {
        if (null == head || null == head.next) {
            return null;
        }

        ListNode flow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            flow = flow.next;
            fast = fast.next.next;
            if (flow == fast) {
                ListNode ptr = head;
                while (ptr != fast){
                    ptr = ptr.next;
                    fast = fast.next;
                }
                return ptr;
            }
        }
        return null;
    }

}
