package com.demo.ThreadDemo;

import java.util.concurrent.*;

public class ThreadDemo9 {


    private static LinkedBlockingQueue queue = new LinkedBlockingQueue<>(2);

    public static void main(String[] args) throws Exception {

        ExecutorService service = new ThreadPoolExecutor(1, 1, 2000, TimeUnit.MILLISECONDS,
                queue, Executors.defaultThreadFactory(), new MyPolicy());


        for (int i = 0; i < 100; i++) {
            try {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread() + " hello world");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread() + " done.");
                    }
                });

//                System.out.println(submit.get());
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }

    private static class MyPolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            BlockingQueue<Runnable> queue = executor.getQueue();

            System.out.println("discard ." + queue);
        }
    }
}
