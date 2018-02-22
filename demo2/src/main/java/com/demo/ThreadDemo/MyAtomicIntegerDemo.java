package com.demo.ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyAtomicIntegerDemo {

//    private static MyCASInteger ival = new MyCASInteger(0);
    private static MyVersionInteger ival = new MyVersionInteger(0);
    private static Object lock = new Object();

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 10000; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        int old, version;
                        synchronized (lock) {
                            old = ival.getValue();
                            version = ival.getVersion();

                            if (ival.set(old + 1, version)) {
//                            System.out.println("ok");
                                break;
                            } else {
                                System.out.println("fail.");
                            }
                        }

                    }

                }
            });
        }

        service.shutdown();

        while (!service.isTerminated()) {

        }

//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }

        System.out.println("ival=" + ival.getValue());
    }
}
