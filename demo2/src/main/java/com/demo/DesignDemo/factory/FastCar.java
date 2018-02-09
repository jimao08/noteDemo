package com.demo.DesignDemo.factory;

import com.demo.DesignDemo.factory.Car;

public class FastCar implements Car {

    @Override
    public void run() {
        System.out.println("fast...");
    }
}
