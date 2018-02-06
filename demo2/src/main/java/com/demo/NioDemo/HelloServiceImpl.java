package com.demo.NioDemo;

public class HelloServiceImpl implements HelloService{

    @Override
    public String hello(String name) {
        return "hello " + name;
    }


    @Override
    public String hello(Person person) {
        if (person == null) {
            return null;
        }

        return person.getFirstName() + ">" + person.getLastName();
    }
}
