package com.net.nio;

/**
 * @author lxq
 * @date 2021年04月16日 13:35
 */
public class Test {

    public void test(){
        while (true){
            System.out.println(11111111);
        }
    }

    public static void main(final String... args) throws InterruptedException {
        Test t = new Test();
        t.test();
    }

}

