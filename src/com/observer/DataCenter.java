package com.observer;

import java.util.List;
import java.util.ArrayList;

public class DataCenter {
    private String name;
    private boolean fire;
    private List<Observer> obsers = new ArrayList<>();
    
    public DataCenter(String name, boolean fire) {
        this.name = name;
        this.fire = fire;
    }
    
    public DataCenter addListener(Observer o){
        this.obsers.add(o);
        return this;
    }
    
    public DataCenter removeListener(Observer o){
        this.obsers.remove(o);
        return this;
    }

    public void fire(){
        this.fire = true;
        Event<DataCenter> p = new Event<>(System.currentTimeMillis() + ":" + this.name + "着火了。", this);
        obsers.forEach(obsers -> obsers.action(p));
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
