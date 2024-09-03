package com.angorithm;

public class 链表相交 {

    public static void main(String[] args) {

        ListNode a5 = ListNode.build().buildVal(6);
        ListNode a4 = ListNode.build().buildVal(5).buildNext(a5);
        ListNode a3 = ListNode.build().buildVal(4).buildNext(a4);
        ListNode a2 = ListNode.build().buildVal(3).buildNext(a3);
        ListNode a1 = ListNode.build().buildVal(2).buildNext(a2);
        ListNode a = ListNode.build().buildVal(1).buildNext(a1);

        ListNode b4 = ListNode.build().buildVal(5);
        ListNode b3 = ListNode.build().buildVal(4).buildNext(b4);
        ListNode b2 = ListNode.build().buildVal(3).buildNext(b3);
        ListNode b1 = ListNode.build().buildVal(2).buildNext(b2);
        ListNode b = ListNode.build().buildVal(1).buildNext(b1);

        ListNode intersect = IsIntersect(a, b);
        if(intersect != null){
            System.out.println("true, 相交点为：" + intersect.getVal());
        }else{
            System.out.println(false);
        }
    }

    private static ListNode IsIntersect(ListNode a, ListNode b) {
        ListNode yd = a;
        boolean ydf = false;
        ListNode xw = b;
        boolean xwf = false;

        while(yd != xw && !(yd == null || xw == null)){
            yd = yd.next;
            xw = xw.next;

            if(yd == null && !ydf){
                yd = b;
                ydf = true;
            }

            if(xw == null && !xwf){
                xw = a;
                xwf = true;
            }
        }

        if(yd == xw){
            return yd;
        }
        return null;
    }
}
