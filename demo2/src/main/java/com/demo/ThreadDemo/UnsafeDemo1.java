package com.demo.ThreadDemo;

import java.util.concurrent.locks.LockSupport;


/**
 * LockSupport demo
 */
public class UnsafeDemo1 {

    public static void main(String[] args) throws Exception{

        final Thread parkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });

        parkThread.start();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LockSupport.unpark(parkThread);
            }
        });

        thread.start();
    }
}
