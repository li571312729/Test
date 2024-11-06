package com.test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class test {

    public static void main(String[] args) {
        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(111111);

    }

}
