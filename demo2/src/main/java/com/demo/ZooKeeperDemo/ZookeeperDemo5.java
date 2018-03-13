package com.demo.ZooKeeperDemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ZookeeperDemo5 extends Thread {

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ZookeeperDemo5 demo = new ZookeeperDemo5();
        demo.setName("send thread");
        demo.start();

        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int ival = 0;
                while (true) {
                    ival++;
                    try {
                        Thread.sleep(10);
                        queue.add(ival + "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"add thread");

        addThread.start();

    }


    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {

        while (true) {
            try {
                String poll = queue.take();

                System.out.println("send msg:" + poll);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
