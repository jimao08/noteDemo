package com.demo.NioDemo;

public class RpcTest0 {

    public static void main(String[] args) throws Exception {
        RpcClient0 client = new RpcClient0();


//        Object invoke = client.invoke(new Object[]{"world !"});

//        Object invoke = client.invoke(new Person("first", "last"));

        HelloService helloService = client.create(HelloService.class);
        String invoke = helloService.hello("world!");
        Person first = helloService.find("first");

        System.out.println(first);


        TestService testService = client.create(TestService.class);
        System.out.println(testService.print(new Person("hhe", "haha")));

        client.close();

    }
}
