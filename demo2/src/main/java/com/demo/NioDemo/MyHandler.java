package com.demo.NioDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler{

    private Class aClass;
    private RpcClient0 client;

    public MyHandler(Class aClass,RpcClient0 client0) {
        this.aClass = aClass;
        this.client = client0;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke " + method.getName());
        Object invoke = client.invoke(aClass, method, args);

        return invoke;
    }
}
