package com.baili.test;

public class BuilderMode{
    public static void main(String[] args)
    {
        Builder builder=new ConcreteBuilder();
        Director director=new Director(builder);
        Product product=director.construct();
        product.show();
    }
}

//产品类
class Product
{
    private String partA;
    private String partB;
    private String partC;
    public void setPartA(String partA)
    {
        this.partA=partA;
    }
    public void setPartB(String partB)
    {
        this.partB=partB;
    }
    public void setPartC(String partC)
    {
        this.partC=partC;
    }
    public void show()
    {
        System.out.println(partA + "-" + partB + "" + partC);
    }
}

//抽象建造者
abstract class Builder
{
    //创建产品对象
    protected Product product=new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();
    //返回产品对象
    public Product getResult()
    {
        return product;
    }
}

//具体建造者
class ConcreteBuilder extends Builder
{
    public void buildPartA()
    {
        product.setPartA("A产品");
    }
    public void buildPartB()
    {
        product.setPartB("B产品");
    }
    public void buildPartC()
    {
        product.setPartC("C产品");
    }
}

//指挥者
class Director
{
    private Builder builder;
    public Director(Builder builder)
    {
        this.builder=builder;
    }
    //产品构建与组装方法
    public Product construct()
    {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}

