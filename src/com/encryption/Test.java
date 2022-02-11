package com.encryption;

import lombok.var;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author lxq
 * @date 2021年03月24日 11:26
 */

public class Test {

    private String s;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Constructor<Test> test = Test.class.getConstructor(String.class);
        Test test1 = test.newInstance("123");
        System.out.println(test1);

        Test test2 = Test.class.newInstance();
        System.out.println(test2);
    }


    public Test(String s) {
        this.s = s;
    }

    public Test() {
    }

    @Override
    public String toString() {
        return "Test{" +
                "s='" + s + '\'' +
                '}';
    }
}

