package com.demo.MapDemo;

import java.util.Hashtable;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentHashMapDemo {

    private static ReentrantLock lock = new ReentrantLock();

    private static int value = 0;

    public static void inc() {
        lock.lock();

        try {
            value++;
        } catch (Exception ex) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock lock = reentrantReadWriteLock.writeLock();

        ConcurrentHashMap<Integer,Object> map = new ConcurrentHashMap();
        map.put(123, "123456");


        AtomicInteger ival = new AtomicInteger(10);
        System.out.println(ival.incrementAndGet());

        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 40000; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    inc();
                }
            });
        }

        es.shutdown();
        while (!es.isTerminated()) {
        }

//
//        for (int i = 0; i < 10000; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    value++;
//                }
//            }).start();
//        }
//
//
//
//        while (Thread.activeCount() > 1) {
//
//        }

        System.out.println(value);


    }
}
