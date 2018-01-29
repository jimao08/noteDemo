package com.demo.ThreadDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo9 {


    private static MyQueue queue = new MyQueue(2);
    private static ExecutorService service;
    private static AtomicInteger doneCount = new AtomicInteger();

    public static void main(String[] args) throws Exception {

        service = new ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS,
                queue, new MyThreadFactory(), new MyPolicy());

//        ((ThreadPoolExecutor) service).allowCoreThreadTimeOut(true);

        ((ThreadPoolExecutor) service).allowCoreThreadTimeOut(true);


        for (int i = 0; i < 100; i++) {
            try {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        final int done = doneCount.incrementAndGet();
                        System.out.println(Thread.currentThread() + " hello world" + done);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread() + " done" + done);
                    }
                });

//                System.out.println(submit.get());
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

//        service.shutdown();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                List<String> list = new ArrayList<>();

                for (int i = 0; i < 10000; i++) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                    String format = simpleDateFormat.format(new Date());

                    list.add(UUID.randomUUID().toString());

                    for (int j = 0; j < 100; j++) {
                        Collections.sort(list);
                    }

                    if (i == 10000 -1) {
                        i = 1;
                    }
                }
            }
        });
        thread2.start();
    }

    private static class MyPolicy implements RejectedExecutionHandler {

        private static AtomicInteger count = new AtomicInteger();

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            BlockingQueue<Runnable> queue = executor.getQueue();

            System.out.println("discard " + count.incrementAndGet() + " >>" + queue);
        }
    }

    private static class MyQueue extends LinkedBlockingQueue {

        private static AtomicInteger pollCount = new AtomicInteger();

        public MyQueue() {
        }

        public MyQueue(int capacity) {
            super(capacity);
        }

        public MyQueue(Collection c) {
            super(c);
        }

        @Override
        public Object take() throws InterruptedException {
            Object take = super.take();
            System.out.println("take..." + take + ">>size=" + size());
            return take;
        }

        @Override
        public void put(Object o) throws InterruptedException {
            super.put(o);
        }

        @Override
        public boolean offer(Object o, long timeout, TimeUnit unit) throws InterruptedException {
            return super.offer(o, timeout, unit);
        }

        @Override
        public boolean offer(Object o) {
            return super.offer(o);
        }

        @Override
        public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
            final int count = pollCount.incrementAndGet();
            System.out.println("poll start..." + count);
            Object poll = super.poll(timeout, unit);
            System.out.println("poll timeout..." + count + ">>size=" + size());
            return poll;
        }

        @Override
        public Object poll() {
            return super.poll();
        }
    }

    private static class MyThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
//            Thread t = new Thread(group, r,
//                    namePrefix + threadNumber.getAndIncrement(),
//                    0);
//            if (t.isDaemon())
//                t.setDaemon(false);
//            if (t.getPriority() != Thread.NORM_PRIORITY)
//                t.setPriority(Thread.NORM_PRIORITY);

            System.out.println("new thread.");

            Thread t = new Thread(r);
            return t;
        }
    }

}
