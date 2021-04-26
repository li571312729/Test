package com.decorator;

public class TailDecorator extends GameDecorator {

    public TailDecorator(GameObject gameObject) {
        super();
        this.setGameObject(gameObject);
    }

    @Override
    void print() {
        gameObject.print();
        if(gameObject instanceof GameDecorator){
            gameObject = getSource(gameObject);
        }
        System.out.println(gameObject.getName() +  "--开启尾焰喷射。");
    }
    
}
