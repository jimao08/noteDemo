package com.demo.DesignDemo.factory;


/**
 * 简单工厂模式
 */
public class AnimalSimpleFactory {

    public static Animal getAnimal(String type) {
        if (type.equals("cat")) {
            return new Cat();
        } else if (type.equals("dog")) {
            return new Dog();
        } else {
            return null;
        }
    }
}
