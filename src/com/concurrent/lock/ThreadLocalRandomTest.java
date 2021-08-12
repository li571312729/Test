package com.concurrent.lock;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lxq
 * @date 2021年08月10日 10:13
 */
public class ThreadLocalRandomTest {

    public static void main(String[] args) {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        while (true){
            int i = current.nextInt(100) + 1;
            System.out.println(i);
            if(i == 1 || i == 100){
                break;
            }
        }

    }


}
