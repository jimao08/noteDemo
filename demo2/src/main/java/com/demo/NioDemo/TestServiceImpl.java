package com.demo.NioDemo;

public class TestServiceImpl implements TestService {

    @Override
    public String print(Person person) {
        return person.toString();
    }
}
