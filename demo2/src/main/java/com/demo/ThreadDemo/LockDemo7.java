package com.demo.ThreadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


/**
 * ThreadLocal demo
 */
public class LockDemo7 {

    static ThreadLocal<List<Integer>> localList = ThreadLocal.withInitial(new Supplier<List<Integer>>() {
        @Override
        public List<Integer> get() {
            return new ArrayList<>();
        }
    });

    static List<List<Integer>> saveList = new ArrayList<>();

    public static void main(String[] args) {


        for (int i = 0; i < 10; i++) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Integer> list = localList.get();
                    for (int j = 0; j < num; j++) {
                        list.add(j);
                    }

                    synchronized (saveList) {
                        saveList.add(list);
                    }
                }
            }).start();
        }



        while (Thread.activeCount() > 1) {
            Thread.yield();
        }


        System.out.println(localList.get());


        for (List<Integer> integerList : saveList) {
            System.out.println(integerList);
        }
    }

}
