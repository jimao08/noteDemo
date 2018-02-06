package com.demo.NioDemo;

public class RpcTest0 {

    public static void main(String[] args) throws Exception {
        RpcClient0 client = new RpcClient0();


//        Object invoke = client.invoke("world !");

        Object invoke = client.invoke(new Person("first", "last"));

        System.out.println(invoke);
        client.close();

    }
}
