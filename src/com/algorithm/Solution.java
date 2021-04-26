package com.algorithm;

/* 自定义java链表类 */
class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { this.val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val + "}";
    }
}

public class Solution {
    
    private static ListNode listNode;

    public static void main(String[] args){
        int[] a = new int[]{3, 7, 9, 3, 5, 8, 0};
        listNode = buildListNode(a);
        //deleteNode(testNode);
        removeNthFromEnd(listNode, 1);
    }
    
    //建立一个链表
    private static ListNode buildListNode(int[] a) {
        ListNode tempNode, firstNode = null, nextNode = null;
        for(int i : a){
            tempNode = new ListNode(i);
            if(firstNode == null){
                firstNode = tempNode;   
                nextNode = tempNode;    //这里其实first 和 next 都指向一个地址
            }else{  
                nextNode.next = tempNode;   //所以这里第二个元素其实已经绑到first上了，，后续就只绑到next上
                nextNode = tempNode;
                if(i == 6){
                }
            }
        }
        return firstNode;
    }

    //删除指定链表节点
    /**
     * @param node
     * 直接将该节点后一个节点值覆盖该节点，然后将该节点next指向next的next。
     */
    public static void deleteNode(ListNode node) {
        if(node.next == null){
            return;
        }else{
            //交换结点和其后续结点中的数据
            node.val = node.next.val;
            node.next = node.next.next;
        }        
    }
    
    /**
     * @param head
     * @param n
     * 删除倒数第几个节点,返回头结点
     * 思想是一遍循环，两个指针，一个指向结尾的，另一个直接指向要删除的前一位，
     * 怎么确定前一位就通过参数n确定两者间的距离，例如倒数第2，，则要删除的距离尾节点为1长度，那删除的前一位距离就是2
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode tempNode = head;
        ListNode removeNode = pre;  //要删除的节点的前一个节点，为什么不直接等于要删除的节点，是为了解决只有一个元素的或者删除最后一位的情况
        int dist = 1;   //要删除的节点和尾节点的距离
        while (tempNode.next != null) {
            tempNode = tempNode.next;
            if(dist < n){
                dist ++;
            }else{
                removeNode = removeNode.next;
            }
        }
        removeNode.next = removeNode.next.next;
        return pre.next;
    }
}
