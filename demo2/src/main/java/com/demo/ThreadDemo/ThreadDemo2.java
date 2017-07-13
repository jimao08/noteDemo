package com.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 */
public class ThreadDemo2 {
    static List<Future<Integer>> futures = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        List<Integer> results = new ArrayList<>();


        ExecutorService executor = Executors.newCachedThreadPool();


        for (int i = 0; i < 100000; i++) {
            Future<Integer> future = executor.submit(new DemoTask(i));

            System.out.println("add future" + Thread.currentThread().getName());
            futures.add(future);
        }

//        while (!executor.isShutdown()) {
//
//        }

        for (Future<Integer> future : futures) {
            results.add(future.get());
        }

        System.out.println(results.size());
    }
}

class DemoTask implements Callable<Integer> {
    private int time;

    public DemoTask(int time) {
        this.time = time;
    }

    @Override
    public Integer call() throws Exception {
//        System.out.println(Thread.currentThread().getName() + ">>start");
//        System.out.println(Thread.currentThread().getName() + ">>end");
//        try {
//            System.out.println(Thread.currentThread().getName() + ">>start");
//            Thread.sleep(time * 1000);
//            System.out.println(Thread.currentThread().getName() + ">>end");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return time;
    }
}
