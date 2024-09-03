package com.designMode.visitor;

public class Main {
    
    public static void main(String[] args) {
        PersonalVisitor p = new PersonalVisitor();
        CompanyVisitor p1 = new CompanyVisitor();
        new Computer().accept(p);
        new Computer().accept(p1);
        System.out.println(p.getTotalPrice());
        System.out.println(p1.getTotalPrice());
    }
    

}
