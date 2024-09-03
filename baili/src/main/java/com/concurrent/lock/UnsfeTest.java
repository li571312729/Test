package com.concurrent.lock;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author lxq
 * @date 2021年08月03日 14:46
 */
public class UnsfeTest {

    static Unsafe unsafe;

    static long stateOffset;
    
    private volatile long state = 0;

    static {
        try {
            unsafe = reflectGetUnsafe();
            stateOffset = unsafe.objectFieldOffset(UnsfeTest.class.getDeclaredField("state"));
            System.out.println(stateOffset);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        UnsfeTest unsfeTest = new UnsfeTest();
        // 因为上面知道了state的偏移量为stateOffset，而且state初始值为0，所以这里能够进行比较替换
        boolean b = unsafe.compareAndSwapLong(unsfeTest, stateOffset,0, 100);
        System.out.println(b);
    }

    // 获取unsafe对象
    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
