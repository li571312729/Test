package com.iterator;

public class Main {
    public static void main(String[] args) {
        Collection_<String> arrayList = new ArrayList_<>();
        for (int i = 0; i < 15; i++) {
            arrayList.add("S" + i);
        }
        
        Iterator_<String> iter = arrayList.iterator();
        while(iter.hasNext()){
            String a = iter.next();
            System.out.print(a + "  ");
        }
        
        System.out.println("\n ---------------");
        
        Collection_<String> linkedList = new LinkedList_<>();
        for (int i = 0; i < 20; i++) {
            linkedList.add("S" + i);
        }
        
        Iterator_<String> iter1 = linkedList.iterator();
        while(iter1.hasNext()){
            System.out.print(iter1.next() + "  ");
        }
    }
}
