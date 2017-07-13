package com.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 */
public class ThreadDemo3 {
    private static List<Integer> saveList = new ArrayList<>();
    private static volatile AtomicInteger saveNumber = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
//            executorService.submit(new ReadTask());
            executorService.submit(new WriteTask());
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.println(">>>");
        }

        System.out.println(saveList.size());

        System.out.println(saveList.get(saveList.size() - 1));
    }

    static class ReadTask implements Runnable {
        @Override
        public void run() {
            if (saveList.size() > 0) {
                System.out.println(saveList.get(0));
            }
        }
    }


    static class WriteTask implements Runnable {
        @Override
        public void run() {
            int andIncrement = saveNumber.getAndIncrement();
            saveList.add(andIncrement);
        }
    }
}



