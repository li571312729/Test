//package com.baili.test;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import com.baili.*;
//import com.baili.test.Test8.InerClass;
//import com.baili.test.Test8.InerClass2;
//import static java.lang.Math.*;
//
//import java.awt.List;
//
///**
// *
// * @author Administrator
// * @description
// */
//public class Test8 extends Test9{
//
//    public static final double PI = 3.123123;
//
//    class InerClass{
//        private void getSum(){
//            System.out.println("Test8.InerClass.getSum():" + (count + number));
//        }
//    }
//
//    static class InerClass2{
//        private void getSum(){
//            System.out.println("Test8.InerClass.getSum():" + (count + number));
//        }
//    }
//
//    private static int count;
//    private static int number = 0;
//    private static int num = 0;
//
//    public Test8(int a, int b) {
//        super(a, b);
//    }
//
//    static{
//        count = 0;
//    }
//
//    {
//        count ++;
//    }
//
//    public void p(Integer a){
//        InerClass in = new InerClass();
//        System.out.println(a);
//    }
//
//    public void p(String a){
//        System.out.println(a);
//    }
//
//    public static void main(String[] args, int a) throws Exception {
//        List list = new List();
//        Map<String, String> map = new HashMap<String, String>(16);
//        Calculable calculable = (int aC, int bC) -> aC + bC;
//        System.out.println(Math.PI);
//        int s = 10;
//        new Test8(23, 34){
//            @Override
//            public void p(Integer a) {
//
//                System.out.println("这是匿名内部类中的放大" + count + s);
//
//            }
//
//            public void c(Integer a) {
//                System.out.println("这是匿名内部类中的放大");
//
//            }
//        }.c(23);
//
//        Test8.InerClass in = new Test8(12, 34).new InerClass();
//        Test8.InerClass2 in2 = new Test8.InerClass2();
//
//        in.getSum();
//        AA AA = new AA();
//        AA.prnint();
//
//        Test9 test = new Test8(12, 34);
//        System.out.println(test instanceof Test8);
//        Test8 test9 = (Test8) test;
//
//        // Thread establish = new Thread(){
//        // @Override
//        // public void run() {
//        // while(true){
//        //
//        // new Test8();
//        // }
//        // }
//        // };
//        // establish.start();
//
//        Runnable establish = new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//            }
//        };
//        new Thread(establish).start();
//    }
//
//    /**
//     * @author Administrator
//     * @updateRemark asd
//     */
//    public void finalize() {
//
//        System.out.println(new Date().getSeconds() + "--目前存活Test8数量：" + -- this.count + "-----共计销毁数量：" + ++ this.number);
//    }
//}
//
//class AA {
//    public void prnint(){
//        System.out.println("AA.prnint()");
//    }
//
//    public static void main(String[] args){
//        InerClass2 in = new Test8.InerClass2();
//    }
//}
//
//@FunctionalInterface
//interface Calculable {
//    // 计算两个int数值
//    int calculateInt(int a, int b);
//}
