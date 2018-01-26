package com.demo.ThreadDemo;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo10 {


    private static ReentrantLock lock = new ReentrantLock();

    private static int ival;

    public static void main(String[] args) throws Exception{


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    Thread.sleep(2200000);
                    ival++;

                    System.out.println("bibi");
                } catch (Exception ex) {

                    System.out.println(Thread.currentThread().getState());
                    if (Thread.interrupted()) {
                        System.out.println("hello");
                    }

                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        thread1.setName("thread1");
        thread1.start();

//        .start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    lock.lock();
                    ival++;
                    System.out.println(ival);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        thread2.setName("thread2");
        thread2.start();


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    lock.lock();
                    ival++;
                    System.out.println(ival);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        t3.setName("t3");
        t3.start();

    }
}
