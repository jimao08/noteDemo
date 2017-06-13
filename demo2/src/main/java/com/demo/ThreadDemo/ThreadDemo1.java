package com.demo.ThreadDemo;

import com.demo.AnnotationDemo.MyAnnotation;

/**
 * 测试AtomicInteger
 * Created by linkang on 17-6-7.
 */

@MyAnnotation(sval = "abcdef")
public class ThreadDemo1 {
    public static int total;
    public static Object obj = new Integer(123);

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        ThreadDemo1 test = new ThreadDemo1();
        test.total = new Integer(0);

        Thread t1 = new Thread(new ThreadTest());
        Thread t2 = new Thread(new ThreadTest());
        Thread t3 = new Thread(new ThreadTest());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(test.total);

        long endTime = System.currentTimeMillis();
        System.out.println("use time=" + (endTime - startTime));
    }


    static class ThreadTest implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 5000000; i++) {
//                    test.total.incrementAndGet();
                synchronized (obj) {
                    ThreadDemo1.total++;
                }
//                    System.out.println(Thread.currentThread().getName() + ":" + test.total.incrementAndGet());;
            }
        }
    }

    private void method1() {
        System.out.println("hello");
    }

    @MyAnnotation(sval = "hahah", ival = 123)
    public void method2(int ival) {

        System.out.println("print=" + ival);
    }
}


