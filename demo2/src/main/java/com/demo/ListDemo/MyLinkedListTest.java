package com.demo.ListDemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by myle on 2018/01/27 下午 20:16
 */

public class MyLinkedListTest {

    public static void main(String[] args) {

        List<String> list = new MyArrayList<>();


        for (int i = 0; i < 10; i++) {
            list.add("AAA" + i);
        }

        list.add(0, "aaaaa");
        System.out.println(list);

        System.out.println(list.remove("AAA1"));
        System.out.println(list);


        for (int i = 0; i < 100; i++) {
            final int ival = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (list) {
                        list.add("obj" + ival);
                    }
                }
            }).start();
        }


        while (Thread.activeCount() > 1) {
            Thread.yield();
        }


        System.out.println(list.size());
    }
}
