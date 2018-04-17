package com.demo.Controller;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    static {
        System.out.println("init.");
    }


    public void print() {
        System.out.println("hello");
    }
}
