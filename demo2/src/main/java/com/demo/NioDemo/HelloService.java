package com.demo.NioDemo;

public interface HelloService {

    String hello(String name);

    String hello(Person person);

    Person find(String firstName);
}
