package com.designMode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car("car01", "红旗");
        Bicycle bicycle = new Bicycle();

        $Proxy0 proxy0 = new $Proxy0(new ProxyHandler(car));
        proxy0.run();

        //保存生成的动态代理类
        //第一个是1.8的
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        MoveAble m = (MoveAble)Proxy.newProxyInstance(MoveAble.class.getClassLoader(),
                        new Class[]{MoveAble.class},
                        new ProxyHandler(car));
        m.move();
        m.run();
        System.out.println(m.toString());

        System.out.println();
        MoveAble m1 = (MoveAble)Proxy.newProxyInstance(MoveAble.class.getClassLoader(),
                new Class[]{MoveAble.class},
                new ProxyHandler(bicycle));
        m1.run();
    }
}

class ProxyHandler implements InvocationHandler{
    private MoveAble m;

    public ProxyHandler(MoveAble m) {
        this.m = m;
    }

    //proxy生成的代理对象，这里就是外面的 m1, method 触发的方法，（接口里可以有多个方法这里可以根据method对象筛分）, args方法的参数，
    //invoke 是触发方法的返回值，这里原来move返回为void 所以这里invoke为null
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(proxy, method, args);
        Object invoke = method.invoke(m, args);
        after(proxy, method, args);
        return invoke;
    }

    private void after(Object proxy, Method method, Object[] args) {
        System.out.println("method    " + method.getName() + "    end");
    }

    private void before(Object proxy, Method method, Object[] args) {
        System.out.println("method    " + method.getName() + "    start");
    }

    public MoveAble getM() {
        return m;
    }

    public void setM(MoveAble m) {
        this.m = m;
    }
}
