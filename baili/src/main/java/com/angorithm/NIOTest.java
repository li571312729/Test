package com.angorithm;

public class NIOTest {

    private static int count = 0;
    private static final String LOCK = "lock";
    private static Object objectA = new Object();
    private static Object objectB = new Object();
    private static Object objectC = new Object();

    public static void main(String[] args) throws InterruptedException {

//        Thread threadA = new Thread(() -> {
//            synchronized (objectB){
//                for (int i = 1; i < 10; i++) {
//                    try {
//                        System.out.println(Thread.currentThread().getName() + ":" + ++count);
//                        objectB.notify();
//                        objectA.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "threadA");
//
//        Thread threadB = new Thread(() -> {
//            synchronized (LOCK.intern()){
//                for (int i = 1; i < 10; i++) {
//                    try {
//                        System.out.println(Thread.currentThread().getName() + ":" + ++count);
//                        objectC.notify();
//                        objectB.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "threadB");
//
//        Thread threadC = new Thread(() -> {
//            synchronized (LOCK.intern()){
//                for (int i = 1; i < 10; i++) {
//                    try {
//                        System.out.println(Thread.currentThread().getName() + ":" + ++count);
//                        objectA.notify();
//                        objectC.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "threadC");
//        threadA.start();
//        threadB.start();
//        threadC.start();


        quicklySort(new int[]{3, 3, 2, 6, 7, 1}, 0, 5);
        // 7 5 2 6 3 1


    }


    public static void quicklySort(int[] arr, int low, int high){
        int middle = getMiddle(arr, low, high);
        quicklySort(arr, low, middle);
        quicklySort(arr, middle + 1, high);
    }

    private static int getMiddle(int[] arr, int low, int high) {
        while (low < high){
            while (arr[low] > arr[high] && low < high){
                high--;
            }
            if (arr[high] > arr[low]){
                swap(arr, high, low);
            }

            while (arr[low] > arr[high] && low < high){
                low++;
            }
            if (arr[high] > arr[low]){
                swap(arr, high, low);
            }
        }
        return low;
    }

    private static void swap(int[] arr, int i, int i1) {
        arr[i] ^= arr[i1];
        arr[i1] ^= arr[i];
        arr[i] ^= arr[i1];
    }

}
