package com.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadDemo11 {
//    private static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

    private static AtomicLong total = new AtomicLong(0);


    public static void main(String[] args) throws Exception {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 100000; i++) {

            final int ival = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    long stime = System.currentTimeMillis();
                    list.add(ival);
                    total.addAndGet(System.currentTimeMillis() - stime);
                }
            });
        }


        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println(total.get());
        System.out.println(list.size());



    }

}
