package com.demo.DesignDemo;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by linkang on 17-6-19.
 */
public class MyObserverObj implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("HELLO:" + this.getClass().getCanonicalName());

        if (o instanceof MyObserable) {
            System.out.println(">>>" + ((MyObserable) o).getIval());
        }

        System.out.println("arg=" + arg);
    }
}
