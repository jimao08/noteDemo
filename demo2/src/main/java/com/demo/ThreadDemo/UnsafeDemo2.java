package com.demo.ThreadDemo;

import sun.misc.Unsafe;


/**
 * park demo
 */
public class UnsafeDemo2 {

    public static void main(String[] args) throws Exception{
        Unsafe unsafe = MyUnsafe.getUnsafe();

        final Thread parkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                unsafe.park(false, 0L);
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

                unsafe.unpark(parkThread);
            }
        });

        thread.start();
    }
}
