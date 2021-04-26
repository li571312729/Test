package com.decorator;

public class RectDecorator extends GameDecorator {

    public RectDecorator(GameObject gameObject) {
        super();
        this.setGameObject(gameObject);
    }

    @Override
    void print() {
        gameObject.print();
        if(gameObject instanceof GameDecorator){
            gameObject = getSource(gameObject);
        }
        System.out.println(gameObject.getName() +  "--护盾启动。");
    }
    
}
