package com.designMode.builder;

public class Main {

    public static void main(String[] args) {
        Personal p = new Personal.PersonalBuilder()
                     .buildBasicInfo("zhangsan", "男", 23)
                     .buildHeight(175)
                     .buildWeight(75)
                     .buildAdress("神官区", "1314")
                     .build();
        System.out.println(p);
    }
}
