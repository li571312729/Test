package com.solution;

import static com.solution.十大经典排序算法.swap;

/**
 * @author lxq
 * @date 2021年08月20日 12:46
 */
public class 全排列 {

    public static void main(String[] args) {
        perm(new int[]{1,2,3}, 0 , 3);
    }

    static void perm(int a[],int n, int m){
        if(n + 1 == m){
            for(int i=0;i<m;i++) {
                System.out.print(a[i] + "  ");
            }
            System.out.println();
            return;
        }
        for(int j = n + 1; j < m; j++){
            swap(a, n, j);
            perm(a,n + 1, m);
            swap(a, j, n);
        }
    }


}
