package com.demo.DesignDemo.factory;


/**
 * 抽象工厂具体类
 */
public class MixFactoryImpl implements MixFactory{

    @Override
    public Animal getAnimal() {
        return new Cat();
    }

    @Override
    public Car getCar() {
        return new FastCar();
    }
}
