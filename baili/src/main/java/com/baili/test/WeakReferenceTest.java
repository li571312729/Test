package com.baili.test;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxq
 * @date 2021年06月16日 9:23
 */
public class WeakReferenceTest {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>(16);
        WeakReference weakReference = new WeakReference(map);
        // 第一次模拟GC的时候因为该弱引用还存在map对象，所以不会被回收
        System.gc();
        System.out.println(weakReference.get());

        // 第二次模拟GC的时候，将map指向置为null，同时在GC前后输出弱引用保存的对象，
        // 发现在GC前弱引用依然还保存有指向map内存地址的指向，
        // 但是GC后因为map不再指向那个内存地址，所以弱引用指向的地址没有别的强引用，则会被回收
        map = null;
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
