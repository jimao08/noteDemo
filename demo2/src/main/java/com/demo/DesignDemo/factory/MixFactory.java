package com.demo.DesignDemo.factory;

/**
 * 抽象工厂具体类
 */
public interface MixFactory {
    Animal getAnimal(String type);

    Car getCar(String type);
}
