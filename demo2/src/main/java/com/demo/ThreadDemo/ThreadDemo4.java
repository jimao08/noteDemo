package com.demo.ThreadDemo;


import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by myle on 2017/7/13.
 */
public class ThreadDemo4 {


    private static volatile int ival;
//    private static int ival;
    //    private static Object lock = new Object();
    private static ReentrantLock myLock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(ival);
        long stime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
//                    synchronized (lock) {
//                        ival++;
//                    }

//                    myLock.lock();
                    ival++;
//                    myLock.unlock();
                }
            });
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.println("wait>>>");
        }


        System.out.println("value=" + ival);

        System.out.println("utime=" + (System.currentTimeMillis() - stime));
    }
}
