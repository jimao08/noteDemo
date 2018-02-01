package com.demo.ThreadDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 */
public class ThreadDemo14 {


    private static ReentrantLock lock = new ReentrantLock(true);
    private static  Condition condition = lock.newCondition();

    private static int ival = 1000;

    public static void main(String[] args) {

        for (int i = 0; i < 1; i++) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }


                    try {

                        Thread.sleep(1000);
                        lock.lockInterruptibly();
                        System.out.println(Thread.currentThread() + " get lock");
                        if (ival > 0) {
                            condition.await();
                        }


                        Thread.sleep(200);
                        System.out.println(Thread.currentThread() + " do some thing.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println(Thread.currentThread() + " unlock.");
                        lock.unlock();
                    }
                }
            });


            thread.setName("t" + (i + 1));
            thread.start();

        }


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    lock.lock();

                    System.out.println(Thread.currentThread() + " get lock");
                    Thread.sleep(200000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    ival = -1;
                    condition.signal();
                    lock.unlock();
                }
            }
        });


        thread2.setName("t2");
        thread2.start();


    }
}
