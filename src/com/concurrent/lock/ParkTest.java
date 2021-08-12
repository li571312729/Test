package com.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author lxq
 * @date 2021年08月12日 16:21
 */
public class ParkTest {

    public void test(){
        LockSupport.park(this);
    }

    public static void main(String[] args) {
        ParkTest p = new ParkTest();
        p.test();
    }

}
