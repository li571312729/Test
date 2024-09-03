package com.baili.test;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.EnumMap;
import java.util.function.Consumer;

public class Test10 {

    public enum DataBasetype{
        MYSQL("mysql"), ORACLE("oracle"), DB2("db2"), SQLSERVER("sqlserver");
        private final String url;
        
        private DataBasetype(String url){
            this.url = url;
        }

        @Override
        public String toString() {
            return super.toString() + "\"" + this.url + '"';
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException {
       Consumer<String> a =  System.out :: println;
       a.accept("hello");
       EnumMap<DataBasetype, String> aEnumMap = new EnumMap<>(DataBasetype.class);
       System.out.println(DataBasetype.valueOf("DB2"));
       
       try {
           // 动态加载xx类的运行时对象
           Class c = Class.forName("java.lang.String");
           // 获取成员方法集合
           Method[] methods = c.getDeclaredMethods();
           // 遍历成员方法集合
           for (Method method : methods) {
               // 打印权限修饰符，如public、protected、private
               System.out.print(Modifier.toString(method.getModifiers()));
               // 打印返回值类型名称
               System.out.print(" " + method.getReturnType().getName() + " ");
               // 打印方法名称
               System.out.println(method.getName() + "();");
           }
       } catch (ClassNotFoundException e) {
           System.out.println("找不到指定类");
       }
       
    }

}
