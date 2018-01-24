package com.demo.ThreadDemo;

public class ThreadDemo12 {

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }));


        Thread.sleep(1000);

        System.out.println("end.");
    }
}
