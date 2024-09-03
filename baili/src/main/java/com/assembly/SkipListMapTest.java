package com.assembly;

public class SkipListMapTest {

    public static void main(String[] args) throws Exception {
        CustomSkipListMap list = new CustomSkipListMap();
        list.put("a", 5);
        list.put("b", 2);
        list.put("c", 12);
        list.put("d", 7);
        list.put("e", 7);
        list.put("f", 7);
        list.remove("a");
        System.out.println(list.get("b"));

        list.put("f1", 7);
        list.put("f2", 7);
        list.put("f3", 7);
        list.put("f4", 7);
        list.put("f1212", 7);
        list.put("f1223", 7);

        System.out.println();
        list.printSkipListLayout();

    }
}
