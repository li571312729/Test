package com.algorithm;

import java.util.Scanner;

public class K个一组翻转链表 {


    public static void main(String[] args) {
        System.out.println("请输入翻转数量K，<= 0则结束。");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        while (k > 0){
            ListNode node = createListNode();
            System.out.print("链表初始化为：");
            printListNode(node);
            node = convertListNodeByK(node, null, k);

            System.out.println(k + "个一组翻转结果为：");
            printListNode(node);

            System.out.println("请输入翻转数量K，<= 0则结束。");
            k = sc.nextInt();
        }
        System.out.println("end");
    }

    private static void printListNode(ListNode node) {
        while (node != null){
            System.out.print(node.getVal());
            if(node.next != null){
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11
     * 举例：k = 3,剩下不足3的不变
     * 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 9 -> 8 -> 7 -> 10 -> 11
     * @param node
     * @param k
     */
    private static ListNode convertListNodeByK(ListNode node, ListNode prev, int k) {
        //第一步判断当前待翻转的链表长度是否满足K
        int count = 0;
        ListNode temp = node;
        while (temp != null && count < k){
            temp = temp.next;
            count ++;
        }

        //第二步开始翻转k个元素
        if(count < k){
            return node;
        }

        temp = node;
        int convertCount = 0;
        ListNode current = temp;
        ListNode next = null;
        while (convertCount < k) {
            if(next == null){
                next = current.next;
                current.next = null;
            }
            ListNode nextTwo = next.next;
            if(++convertCount < k){
                next.next = current;
                current = next;
                next = nextTwo;
            }
        }

        //1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11
        if(prev != null){
            prev.next = current;
        }
        node.next = next;
        if(next != null){
            convertListNodeByK(next, node, k);
        }
        return current;
    }

    private static ListNode createListNode() {
        ListNode node = ListNode.build().buildVal(1);
        ListNode node1 = ListNode.build().buildVal(2);
        ListNode node2 = ListNode.build().buildVal(3);
        ListNode node3 = ListNode.build().buildVal(4);
        ListNode node4 = ListNode.build().buildVal(5);
        ListNode node5 = ListNode.build().buildVal(6);
        ListNode node6 = ListNode.build().buildVal(7);
        ListNode node7 = ListNode.build().buildVal(8);
        ListNode node8 = ListNode.build().buildVal(9);
        ListNode node9 = ListNode.build().buildVal(10);
        ListNode node10 = ListNode.build().buildVal(11);
        node.buildNext(node1);
        node1.buildNext(node2);
        node2.buildNext(node3);
        node3.buildNext(node4);
        node4.buildNext(node5);
        node5.buildNext(node6);
        node6.buildNext(node7);
        node7.buildNext(node8);
        node8.buildNext(node9);
        node9.buildNext(node10);
        return node;
    }

}
