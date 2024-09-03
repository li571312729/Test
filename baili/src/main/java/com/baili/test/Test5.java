package com.baili.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test5 {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        HashMap<String, String> test = new HashMap<>();
        
        Class<?> mapClass = test.getClass();
        Method capacity = mapClass.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("Map capacity：" + capacity.invoke(test) + ",size：" + test.size());
        
        ArrayList<Integer> list = new ArrayList<>(12);
        list.add(2);
        list.add(3);
        getListCapacity(list);
        list.trimToSize();
        getListCapacity(list);
        
        int a = 2 ^ 3;
        System.out.println(a);
        
    }
    
    public static void getListCapacity(List list) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
        Class<?> listclass = list.getClass();
        Field capacity = listclass.getDeclaredField("elementData");
        capacity.setAccessible(true);
        System.out.println("List capacity：" + ((Object[])capacity.get(list)).length + ",size：" + list.size());
    }

}
