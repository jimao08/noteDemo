package com.demo.ThreadDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo13 {
    private static int runCount = 1000;
    private static ReentrantLock lock = new ReentrantLock(false);

    public static void main(String[] args) {

        LinkedBlockingQueue queue = new LinkedBlockingQueue(20);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        lock.lock();


                        if (runCount < 0) {
                            break;
                        }
                        runCount--;
                        Thread.sleep(10);
                        System.out.println("thread 1 get lock.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        t1.setName("t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        lock.lock();

                        if (runCount < 0) {
                            break;
                        }
                        runCount--;
                        Thread.sleep(10);
                        System.out.println("thread 2 get lock.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        t2.setName("t2");
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        lock.lock();
                        if (runCount < 0) {
                            break;
                        }
                        runCount--;
                        Thread.sleep(10);
                        System.out.println("thread 3 get lock.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        t3.setName("t3");
        t3.start();

    }
}
