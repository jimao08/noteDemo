package com.demo.ThreadDemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LockDemo8 {

    private static List<Long> sizes = new ArrayList<>();
    private static final int STAT_COUNT = 4;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    System.out.println(Thread.currentThread() + " start.");
                    synchronized (sizes) {
                        if (sizes.size() != STAT_COUNT) {
                            System.out.println(Thread.currentThread() + " wait.");
                            sizes.wait();
                        }

                        long total = 0;
                        for (Long size : sizes) {
                            total += size;
                        }

                        System.out.println("total=" + total + " bytes," + (total/1024/1024/1024) + "GB");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {

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

                    if (sizes.size() == STAT_COUNT) {
                        sizes.notify();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                }

            }
        }
    }
}

