package com.angorithm;

/**
 * @author lxq
 * @date 2021年06月30日 9:38
 */
public class 合并两个有序的数组 {

    public static void main(String[] args) {
        merge1(new int[]{1,0,0,0,0,0}, 1, new int[]{}, 0);
    }

    /**
     * 合并两个有序的数组
     * 输入：
     *      [4,5,6],[1,2,3]
     * 返回值：
     *      [1,2,3,4,5,6]
     * @author lxq
     * @date 2021/6/30 9:39
     * @param A
     * @param m
     * @param B
     * @param n 
     */
    public static void merge(int A[], int m, int B[], int n) {
        if(m < 1){
            for (int i = 0; i < B.length; i++) {
                A[i] = B[i];
            }
        }

        if(m >= 1){
            for (int i : B) {
                for (int j = m - 1; j >= 0; j--) {
                    if(i < A[j]){
                        A[j + 1] = A[j];
                        A[j] = i;
                    }else {
                        A[j + 1] = i;
                        break;
                    }
                }
                m ++;
            }
        }
    }

    /**
     * 合并两个有序的数组
     * 输入：
     *      [4,5,6],[1,2,3]
     * 返回值：
     *      [1,2,3,4,5,6]
     *
     *      从两个末尾开始 比较最后一个数的大小，大的放在 m + n - 1的位置，，依次，，，
     * @author lxq
     * @date 2021/6/30 9:39
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public static void merge1(int A[], int m, int B[], int n) {
        int index = m + n - 1;
        int i = m -1, j = n -1;
        while (i >= 0 && j >= 0){
            if(A[i] > B[j]){
                A[index--] = A[i --];
            }else {
                A[index--] = B[j --];
            }
        }

        while (j >= 0){
            A[index--] = B[j --];
        }

        while (i >= 0){
            A[index--] = A[i --];
        }
    }
}
