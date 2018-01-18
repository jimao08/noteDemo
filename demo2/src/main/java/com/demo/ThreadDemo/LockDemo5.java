package com.demo.ThreadDemo;

import java.util.concurrent.Semaphore;


/**
 * semaphore demo
 */
public class LockDemo5 {

    private static int ival;
    private static Semaphore semaphore = new Semaphore(1000);


    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new AddTask(10)).start();
            new Thread(new MinusTask(10)).start();

            for (int j = 0; j < 10; j++) {
                Thread lookThread = new Thread(new LookTask());
                lookThread.setName("lookTask " + i + ">>" + j);
                lookThread.start();

            }
        }
    }

    static class AddTask implements Runnable {

        private final int addNum;

        public AddTask(int addNum) {
            this.addNum = addNum;
        }

        @Override
        public void run() {
            try {
                System.out.println("add pre:" + semaphore.availablePermits());
                semaphore.acquire(1000);
                System.out.println("add after:" + semaphore.availablePermits());
//                Thread.sleep(1000);
                ival = ival + addNum;
                System.out.println("add " + addNum + ", current=" + ival);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(1000);
            }
        }
    }


    static class MinusTask implements Runnable {

        private final int minusNum;

        public MinusTask(int minusNum) {
            this.minusNum = minusNum;
        }

        @Override
        public void run() {
            try {
                System.out.println("pre:" + semaphore.availablePermits());

                semaphore.acquire(1000);
                System.out.println("after:" + semaphore.availablePermits());

                ival = ival - minusNum;
                System.out.println("minus " + minusNum + " current=" + ival);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(1000);
            }
        }
    }


    static class LookTask implements Runnable {

        public LookTask() {
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " pre:" + semaphore.availablePermits());

                semaphore.acquire(100);
                System.out.println(Thread.currentThread().getName() + " after:" + semaphore.availablePermits());
                System.out.println(Thread.currentThread().getName() + " look:" + ival);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(100);
            }
        }
    }
}
