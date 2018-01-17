package com.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo3 {
    private static int money = 1000;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition noMoney = lock.newCondition();


    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SaveTask(80));
            thread.setName("save thread" + i);

            threads.add(thread);
        }


        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new GetTask(113));
            thread.setName("get thread" + i);
            threads.add(thread);
        }

        Collections.shuffle(threads);
        threads.forEach(t->t.start());

    }

    private static class SaveTask implements Runnable {
        private final int saveNum;

        public SaveTask(int saveNum) {
            this.saveNum = saveNum;
        }

        @Override
        public void run() {
            try {

                lock.lockInterruptibly();
                System.out.println(Thread.currentThread() + " getlock.");
                money = money + saveNum;
//                Thread.sleep(1000);
                System.out.println("save money:" + saveNum + ",current=" + money);
                noMoney.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
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
            try {
                lock.lockInterruptibly();

//                int current = money;
                while (money - getNum < 0) {
                    System.out.println(Thread.currentThread() + " wait save money.current=" + money);
                    noMoney.await();
                }
                System.out.println(Thread.currentThread() + " getlock.");
                money = money - getNum;
                System.out.println("get money:" + getNum + ",current=" + money);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread() + " unlock.");
            }
        }
    }


}
