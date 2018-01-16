package com.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by myle on 2018/01/16 下午 23:30
 */

public class ThreadDemo8 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    private static boolean flag = false;

    public static void main(String[] args) {


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                try {
                    lock.lock();

                    while (!flag) {
                        System.out.println(Thread.currentThread() + ":" + lock.isLocked() + lock.isHeldByCurrentThread());
                        condition.await();
                        System.out.println(Thread.currentThread() + ":" + lock.isLocked() + lock.isHeldByCurrentThread());
                    }

                    System.out.println(Thread.currentThread() + " do some thing.");
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread() + " done.");
                } catch (Exception e) {
                    System.out.println(list.size());
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(lock.isLocked());
                    System.out.println(lock.isHeldByCurrentThread());
                    lock.lock();

                    while (!flag) {
                        Thread.sleep(3000);
                        flag = true;
                        condition.signal();
                    }


                    System.out.println(Thread.currentThread() + " do some thing2.");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        });


        thread1.start();
        thread2.start();
    }
}
