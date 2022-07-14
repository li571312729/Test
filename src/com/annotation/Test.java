package com.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaoqiangli
 * @Date
 */
public class Test {

    private static String name;
    private String name1;
    private String name2;

    public static void main(String[] args) throws Exception {
        //Class<Test> aClass = Test.class;
        //Method print = aClass.getDeclaredMethod("print", null);
        //Method[] declaredMethods = aClass.getDeclaredMethods();
        //
        //Roles[] annotationsByType = print.getAnnotationsByType(Roles.class);
        //Role[] role = print.getAnnotationsByType(Role.class);
        //Arrays.asList(annotationsByType).forEach(o -> Arrays.asList(o.value()).forEach(t -> System.out.println(t.roleName())));
        //System.out.println("--------------------------");
        //Arrays.asList(role).forEach(o -> System.out.println(o.roleName()));

        System.out.println(a());
    }

    public static boolean a(){
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
        return true;
    }










    //@SuppressWarnings("unchecked")
    @Role(roleName = "roel1")
    @Role(roleName = "roel2")
    @Role(roleName = "roel3")
    //@Roles({@Role(roleName = "222"), @Role(roleName = "3333")})
    public void print() {
        System.out.println(11111111);
    }
}
