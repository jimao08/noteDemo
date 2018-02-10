package com.demo.DesignDemo.factory;


/**
 * 抽象工厂具体类
 */
public class MixFactoryImpl implements MixFactory{

    @Override
    public Animal getAnimal(String type) {
        return new Cat();
    }

    @Override
    public Car getCar(String type) {
        return new FastCar();
    }
}
