package com.demo.DesignDemo.factory;


/**
 * 工厂模式
 */
public class CatFactory implements AnimalAbstractFactory{

    @Override
    public Animal getAnimal() {
        return new Cat();
    }
}
