package com.baili.test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Test7 {
    
    private Integer aInteger = 10;
    
    static Random random = new Random();
    
    static DecimalFormat decimalFormat = new DecimalFormat("######.##");
    
    static SimpleDateFormat sDateFormat = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss");
    
    public static void main(String[] args) {
        
        double[] array = new double[9999];
        long t1 = System.currentTimeMillis();
        fillArray(array);
        long t2 = System.currentTimeMillis();
        System.err.println("数据填充时间:" + (t2 - t1));
        long t3 = System.currentTimeMillis();
        bubblingSort(array);
        long t4 = System.currentTimeMillis();
        System.err.println("冒泡排序时间:" + (t4 - t3)); 
        long t5 = System.currentTimeMillis();
        quickSort(array);
        long t6 = System.currentTimeMillis();
        System.err.println("数据填充时间:" + (t2 - t1));
        System.err.println("冒泡排序时间:" + (t4 - t3));
        System.err.println("快速排序时间:" + (t6 - t5));  
    }
    
    private static void quickSort(double[] array) {
        if(array.length > 0) {
            // 查看数组是否为空
            unckSort(array, 0, array.length-1);
        }
    }
    
    static int a = 0;
    public static void unckSort(double[] array,int low,int high) {
        if(low < high) {
            int middle = getMiddle(array,low,high);    // 将list数组一分为二
            System.out.println("递归排序,第" + (++a) + "次递归。");
            unckSort(array,low,middle-1);    // 对低字表进行递归排序
            unckSort(array,middle+1,high);    // 对高字表进行递归排序
        }
    }
    
    public static int getMiddle(double[] list, int low, int high) {
        double tmp = list[low]; // 数组的第一个值作为中轴（分界点或关键数据）
        while (low < high) {
            while (low < high && list[high] > tmp) {
                high--;
            }
            list[low] = list[high]; // 比中轴小的记录移到低端
            while (low < high && list[low] < tmp) {
                low++;
            }
            list[high] = list[low]; // 比中轴大的记录移到高端
        }
        list[low] = tmp; // 中轴记录到尾
        return low; // 返回中轴的位置
    }

    private static void bubblingSort(double[] array) {
        double temp = 0d;
        for(int i = 0; i < array.length; i ++){
            for(int j = 0; j < array.length - 1 - i; j ++){
                if(array[j] > array[j + 1]){
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println("冒泡,第" + i + "遍遍历。");
        }
    }

    public static void fillArray(Object object){
        if(object instanceof double[]){
            for(int i = 0; i < ((double[])object).length ; i ++){
                ((double[])object)[i] = Double.parseDouble(decimalFormat.format(random.nextDouble()*999999));
            }
        }else{
            System.out.println("数组类型必须是double[]");
        }
    }
    
}
