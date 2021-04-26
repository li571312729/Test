package com.decorator;

public abstract class GameDecorator extends GameObject{
   
    protected GameObject gameObject;

    abstract void print();
    
    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
    
    public GameObject getSource(GameObject gameObject){
        if(gameObject instanceof GameDecorator){
            gameObject = getSource(((GameDecorator)gameObject).getGameObject());
        }
        return gameObject;
    }
}
