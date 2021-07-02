package com.baili.test;



public class Test7 {

    public static void test(A a){
        a.service();
    }

    public static void main(String[] args) {
        test(new C());
    }

}

abstract class A{

    public abstract void service();
}

abstract class B extends A{

    @Override
    public void service() {
        _service();
    }

    abstract public void _service();
}

class C extends B{
    @Override
    public void _service() {
        System.out.println(2222222);
    }
}
