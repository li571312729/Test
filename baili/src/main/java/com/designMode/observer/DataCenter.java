package com.designMode.observer;

import java.util.ArrayList;
import java.util.List;

public class DataCenter implements Target<DataCenter>{
    private String name;
    private boolean fire;
    private List<Observer> obsers = new ArrayList<>();

    public DataCenter(String name, boolean fire) {
        this.name = name;
        this.fire = fire;
    }

    @Override
    public DataCenter addListener(Observer o){
        this.obsers.add(o);
        return this;
    }

    @Override
    public DataCenter removeListener(Observer o){
        this.obsers.remove(o);
        return this;
    }

    @Override
    public void notifyObserver(Event<?> p) {
        obsers.forEach(obsers -> obsers.action(p));
    }

    public void fire(){
        this.fire = true;
        Event<DataCenter> p = new Event<>(System.currentTimeMillis() + ":" + this.name + "着火了。", this);
        notifyObserver(p);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }
    
    
}
