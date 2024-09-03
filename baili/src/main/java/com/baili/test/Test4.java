package com.baili.test;

import java.lang.reflect.Field;

public class Test4 {
    

    public static void main(String args[]) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        System.setProperty("java.library.path", System.getProperty("java.library.path")
                + "C:\\Dependence\\Dll;");
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);

       
        
        System.out.println(System.getProperty("java.library.path"));
    }
    
    public static void test(){
        int a = 3;
        try {
            a ++;
            System.out.println(a);
        } finally {
            a++;
            System.out.println(a);
        }
        System.out.println("test");
        
    }
}
