package com.demo.ThreadDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试多线程map操作
 * Created by myle on 2017/7/13.
 */
public class ThreadDemo5 {

    private static Map saveMap = new HashMap();
    private static Object lock = new Object();
//    private static Map saveMap = new ConcurrentHashMap();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();


        for (int i = 0; i < 1000; i++) {
            executorService.submit(new MapTask(i));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.println("wait>>>");
        }

        System.out.println(saveMap.size());
        System.out.println(saveMap.keySet().size());

    }

    static class MapTask implements Runnable {
        private int ival;

        public MapTask(int ival) {
            this.ival = ival;
        }

        @Override
        public void run() {

            synchronized (lock) {
                saveMap.put(ival, ival);
            }
        }


        //为什么下面这种方式无效
//        @Override
//        public synchronized void run() {
//            saveMap.put(ival, ival);
//        }
    }
}


