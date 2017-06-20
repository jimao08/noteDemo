package com.demo.DesignDemo;

import java.util.Observable;

/**
 * Created by linkang on 17-6-19.
 */
public class MyObserable extends Observable {

    private int ival;

    public void update(int value) {
        System.out.println("a>" + hasChanged());
        ival = value;
        setChanged();
        System.out.println("b>" + hasChanged());
        notifyObservers(ival);
    }

    public synchronized int getIval() {
        return ival;
    }
}
