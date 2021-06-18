package com.solution;

/**
 * @author lxq
 * @date 2021年06月09日 10:27
 */
public class 判断链表是否有环 {

    public static void main(String[] args) {
    }

    /**
     * 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
     * @author lxq
     * @date 2021/6/9 10:34
     * @param head
     * @return boolean
     */
    public static boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null){
            slow = slow.next;
            if(fast.next != null){
                fast = fast.next.next;
            }else {
                return false;
            }

            if(fast == slow){
                return true;
            }
        }
        return false;
    }
}

