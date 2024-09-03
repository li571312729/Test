package com.designMode.observer;

public interface Target<T> {

    public T addListener(Observer o);
    public T removeListener(Observer o);
    public void notifyObserver(Event<?> e);
}
