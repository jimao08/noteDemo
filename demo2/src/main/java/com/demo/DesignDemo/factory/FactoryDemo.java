package com.demo.DesignDemo.factory;

public class FactoryDemo {

    public static void main(String[] args) {
        Animal cat = AnimalSimpleFactory.getAnimal("cat");
        cat.run();


        AnimalAbstractFactory factory = new DogFactory();
        Animal animal = factory.getAnimal();
        animal.run();

    }
}
