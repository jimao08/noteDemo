package com.demo.ThreadDemo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ReentrantLock demo
 */
public class LockDemo0 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Thread thread1;
    private static Thread thread2;
    private static Thread thread3;
    private static Thread thread4;
    private static Thread thread5;

    public static void main(String[] args) throws Exception {

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                    Thread.sleep(1000000);

                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < 500000; i++) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                        String format = simpleDateFormat.format(new Date());

                        list.add(UUID.randomUUID().toString());
                        Collections.sort(list);
                    }

                    System.out.println("thread1 done.");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread() + " interrupt.");
                } finally {
//                    lock.unlock();
                }
            }
        });

        thread1.start();


        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(10);
                    for (int i = 0; i < 100; i++) {
                        System.out.println("try " + i);
                        if (lock.tryLock()) {
                            System.out.println("get lock.");
                            System.out.println("ddd " + lock.getHoldCount());
                            break;
                        }
                    }


                    lock.lockInterruptibly();


                    System.out.println(lock.isHeldByCurrentThread());
                    System.out.println("thread2 done.");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread() + " interrupt.");
                    e.printStackTrace();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        System.out.println(Thread.currentThread() + " unlock...");
                        lock.unlock();
                    }
                }
            }
        });

        thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

                List<String> list = new ArrayList<>();
                for (int i = 0; i < 500000; i++) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                    String format = simpleDateFormat.format(new Date());

                    list.add(UUID.randomUUID().toString());
                    Collections.sort(list);
                }

            }
        });
        thread3.start();


        Object lockObj = new Object();
        thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockObj) {
                    try {
                        System.out.println(Thread.currentThread() + " sleep.");
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockObj) {
                    System.out.println("run...");
                }
            }
        });

        thread4.start();
        thread5.start();



        thread2.start();
        Thread.sleep(30);
//        thread2.interrupt();

        Thread.sleep(1000);
    }
}
