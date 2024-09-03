package com.designMode.decorator;

public class Main {
    public static void main(String[] args) {
        Tank tank = new Tank("虎式");
        Bullet bullet = new Bullet("N95");
        
        //这里的外壳装饰器和尾焰装饰器并没有指定某一个对象使用，凡是能够这样装饰的都可以使用
        RectDecorator tankRect = new RectDecorator(tank);
        TailDecorator tankTail = new TailDecorator(tank);
        //这里因为所有装饰器都有同一个父类，因此两种不同的装饰器可以互相结合
        TailDecorator tankTail1 = new TailDecorator(tankRect);

        RectDecorator bulletRect = new RectDecorator(bullet);
        TailDecorator bulletTail = new TailDecorator(bullet);
        TailDecorator bulletTail1 = new TailDecorator(bulletRect);
        
        tank.print();
        System.out.println("--------------");
        tankRect.print();
        tankTail.print();
        tankTail1.print();
        
        System.out.println();
        bullet.print();
        System.out.println("--------------");
        bulletRect.print();
        bulletTail.print();
        bulletTail1.print();
    }
}
