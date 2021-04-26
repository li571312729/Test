package com.baili.test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class Test11 {

    public void test01(Map<String, Test11> map, ArrayList<Test11> list){
        System.out.println("test01");
    }
    
    public Map<String, Object> test02(){


        System.out.println("test02");
        return null;
    }
    
    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        Method method = Test11.class.getMethod("test01", Map.class, ArrayList.class);
        Type[] t = method.getGenericParameterTypes();
        
        for(Type type : t){
            System.out.println("参数类型：" + type);
            if(type instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                for(Type type2 : actualTypeArguments){
                    System.out.print(type2 + "  ");
                }
                System.out.println();
            }
        }
        
        System.out.println();
        Method method2 = Test11.class.getMethod("test02", null);
        Type genericReturnType = method2.getGenericReturnType();
        System.out.println("返回值类型为：" + genericReturnType);
        if(genericReturnType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for(Type type2 : actualTypeArguments){
                System.out.print(type2 + "  ");
            }
        }
    }

}
