package com.solution;

import java.util.Optional;

/**
 * @author lxq
 * @date 2021年07月26日 13:28
 */
public class NC156数组中只出现一次的数 {

    public static void main(String[] args) {
        String queryUserId = null;
        queryUserId = Optional.ofNullable(queryUserId).orElse("hello");

        System.out.println(queryUserId);

        System.out.println(foundOnceNumber(new int[]{5,4,1,1,5,1,5}, 3));
    }

    /**
     * 给定一个整型数组 arrarr 和一个整数 k(k>1)k(k>1)。
     * 已知 arrarr 中只有 1 个数出现一次，其他的数都出现 k 次。
     * 请返回只出现了 1 次的数。
     *
     * 解法：
     * int 型 32位比特位存储，因此我们将数组中每一个数字按照32位二进制的形式
     * 进行相加，每位上1的个数和再对 k 进行取余，则余数就是那个只出现一次的数在该位的值。
     *
     * @author lxq
     * @date 2021/7/26 13:35
     * @param arr
     * @param k
     * @return int
     */
    public static int foundOnceNumber (int[] arr, int k) {
        int sum = 0;
        // 从高位到地位计算各位1的和，，高->低 只需要右移然后和1做与操作
        for (int i = 31; i >= 0; i--){
            // 每位上1的和
            int count = 0;
            for (int a : arr) {
                count += (a >> i) & 1;
            }
            sum += count % k * Math.pow(2,i);
//            sum = 2*sum + count % k; // 每当算下个低位相当于左移操作，则之前的sum*2
        }
        return sum;
    }


    public int foundOnceNumber1 (int[] arr, int k) {
        // write code here
        int res=0;
        // 这里判断k是否是2的倍数，，如果是则表明除了那一个只出现一次的数以外，其他的数亦或合为0，所以最终亦或结果就是那个只出现一次的数
        if(k%2==0){
            for(int i=0;i<arr.length;i++){
                res^=arr[i];
            }
        }else{
            int bits[]=new int[32];
            for(int a:arr){
                for(int i=0;i<32;i++){
                    bits[i]+=(a&(1<<i));
                }
            }
            for(int i=0;i<bits.length;i++){
                if(bits[i]%k!=0){
                    res|=(1<<i);
                }
            }
        }
        return res;
    }
}
