package com.demo.ThreadDemo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDemo2 {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws Exception {

        LockDemo2 demo2 = new LockDemo2();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        demo2.read();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.setName("read thread " + i);

            thread.start();
        }

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        demo2.write();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.setName("write thread " + i);

            thread.start();
        }

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                List<String> list = new ArrayList<>();
                for (int i = 0; i < 10000; i++) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                    String format = simpleDateFormat.format(new Date());

                    list.add(UUID.randomUUID().toString());
                    Collections.sort(list);
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LockDemo2.class) {
                    System.out.println(Thread.currentThread() + " ....");
                }
            }
        });

        thread3.start();
    }


    public void read() throws InterruptedException {
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            System.out.println(Thread.currentThread() + " read begin.");

            Thread.sleep(15000);
            System.out.println(Thread.currentThread() + "read done.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }


    public void write() throws InterruptedException {
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        try {
            writeLock.lockInterruptibly();
            System.out.println("write begin.");
            Thread.sleep(15000);
            System.out.println("write done.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

}
