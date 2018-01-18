package com.demo.ThreadDemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 失败的示例
 */
public class LockDemo9 {

    private static List<Long> sizes = new ArrayList<>();
    private static final int STAT_COUNT = 4;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition notFinish = lock.newCondition();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    System.out.println(Thread.currentThread() + " start.");
                    synchronized (sizes) {

                        lock.lock();

                        if (sizes.size() != STAT_COUNT) {
                            System.out.println(Thread.currentThread() + " wait.");
                            notFinish.await();
                        }

                        long total = 0;
                        for (Long size : sizes) {
                            total += size;
                        }

                        System.out.println("total=" + total);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {

                    lock.unlock();
                }


            }
        }).start();

        new Thread(new SpaceStatTask("c:\\")).start();
        new Thread(new SpaceStatTask("d:\\")).start();
        new Thread(new SpaceStatTask("e:\\")).start();
        new Thread(new SpaceStatTask("f:\\")).start();


    }


    private static class SpaceStatTask implements Runnable {

        private final String path;

        public SpaceStatTask(String path) {
            this.path = path;
        }

        @Override
        public void run() {
            File file = new File(path);
            long totalSpace = file.getTotalSpace();
            synchronized (sizes) {
                try {
                    sizes.add(totalSpace);
                    System.out.println("add size=" + totalSpace + ",path=" + path);

                    Thread.sleep(1000);

                    lock.lock();
                    if (sizes.size() == STAT_COUNT) {
                        notFinish.signal();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }
}

