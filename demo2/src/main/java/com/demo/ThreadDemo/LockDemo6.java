package com.demo.ThreadDemo;

import java.util.concurrent.CountDownLatch;


/**
 * CountDownLatch demo
 */
public class LockDemo6 {

    private static CountDownLatch latch = new CountDownLatch(10);
    private static int ival;
    private static Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new AddTask(1)).start();
            new Thread(new MinusTask(1)).start();
        }
    }


    private static class AddTask implements Runnable {

        private final int addNum;

        public AddTask(int addNum) {
            this.addNum = addNum;
        }

        @Override
        public void run() {
            try {

                if (latch.getCount() > 0) {
                    System.out.println(Thread.currentThread() + " wait.");
                    latch.await();
                }

                synchronized (lock) {
                    ival = ival + addNum;
                    System.out.println("add " + addNum + ", current=" + ival);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }


    private static class MinusTask implements Runnable {

        private final int minusNum;

        public MinusTask(int minusNum) {
            this.minusNum = minusNum;
        }

        @Override
        public void run() {

            try {

                if (latch.getCount() > 0) {
                    latch.countDown();

                    synchronized (lock) {
                        ival = ival - minusNum;
                        System.out.println("minus " + minusNum + " current=" + ival);
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {

            }

        }
    }

}
