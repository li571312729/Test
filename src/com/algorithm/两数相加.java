package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * @author Administrator
 */
public class 两数相加 {

    public static void main(String[] args) {
        两数相加 a = new 两数相加();
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9)));
        ListNode l2 = new ListNode(9, new ListNode(9));
        a.addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode other = null;
        List<Integer> a = new ArrayList<>();
        // 记录进位值
        int front = 0;

        while(l1 != null || l2 != null ){
            int tmp = front;
            tmp += l1 != null ? l1.val : 0;
            tmp += l2 != null ? l2.val : 0;
            // 如果大于9 则产生进位，截取个位
            if(tmp > 9){
                front = tmp / 10;
                tmp = tmp % 10;
            }else {
                front = 0;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

            if(other == null){
                result.val = tmp;
                other = result;
            }else {
                ListNode c = new ListNode(tmp);
                other.next = c;
                other = c;
            }
        }

        if(front != 0){
            ListNode listNode = new ListNode(front);
            other.next = listNode;
            other = listNode;
        }
        return result;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

}

