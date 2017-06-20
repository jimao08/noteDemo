package com.demo.DesignDemo;

/**
 * Created by linkang on 17-6-19.
 */
public class DesignDemo2 {
    public static void main(String[] args) {
        MyObserable myObserable = new MyObserable();
        myObserable.addObserver(new MyObserverObj());
        myObserable.update(1243);
        myObserable.update(-1);
    }
}
