package com.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized monitor demo
 */
public class LockDemo4 {
    private static int money = 0;
    private static Object lock = new Object();
    private static Object noMoney = new Object();


    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SaveTask(80));
            thread.setName("save thread" + i);

            threads.add(thread);
        }


        List<Thread> getThreads = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new GetTask(40));
            thread.setName("get thread" + i);
            threads.add(thread);
            getThreads.add(thread);
        }

        Collections.shuffle(threads);
        threads.forEach(t -> t.start());

    }

    private static class SaveTask implements Runnable {
        private final int saveNum;

        public SaveTask(int saveNum) {
            this.saveNum = saveNum;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " getlock.");
                money = money + saveNum;
                System.out.println("save money:" + saveNum + ",current=" + money);
                lock.notify();
                System.out.println(Thread.currentThread() + " unlock.");
            }
        }
    }


    private static class GetTask implements Runnable {

        private final int getNum;

        public GetTask(int get) {
            this.getNum = get;
        }

        @Override
        public void run() {

            synchronized (lock) {

                try {
                    while (money - getNum < 0) {
                        lock.wait();
                        System.out.println(Thread.currentThread() + " wait save money.current=" + money);
                    }
                    System.out.println(Thread.currentThread() + " getlock.");
                    money = money - getNum;
                    System.out.println("get money:" + getNum + ",current=" + money);
                    System.out.println(Thread.currentThread() + " unlock.");

                    if (money > 0) {
                        lock.notify();
                    }

                } catch (Exception e) {
                    System.out.println(Thread.currentThread() + " interrupt.");
                    e.printStackTrace();
                }
            }
        }
    }


}
