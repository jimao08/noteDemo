package com.demo.ThreadDemo;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadDemo12 {
    private static CyclicBarrier barrier;

    public static void main(String[] args) {
        barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("======== all threads have arrived at the checkpoint ===========");
            }
        });
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Traveler("Traveler1"));
        service.submit(new Traveler("Traveler2"));
        service.submit(new Traveler("Traveler3"));
        service.shutdown();
    }

    private static class Traveler implements Runnable {
        private final String name;

        Traveler(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Random rand = new Random();
                TimeUnit.SECONDS.sleep(rand.nextInt(5));
                System.out.println(name + " arrived at Beijing.");
                barrier.await();
                TimeUnit.SECONDS.sleep(rand.nextInt(5));
                System.out.println(name + " arrived at Shanghai.");
                barrier.await();
                TimeUnit.SECONDS.sleep(rand.nextInt(5));
                System.out.println(name + " arrived at Guangzhou.");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
