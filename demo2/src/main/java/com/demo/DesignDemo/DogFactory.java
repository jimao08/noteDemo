package com.demo.DesignDemo;


/**
 * 工厂模式
 */
public class DogFactory implements AnimalAbstractFactory{

    @Override
    public Animal getAnimal() {
        return new Dog();
    }
}
