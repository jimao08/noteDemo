package com.demo.JvmDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public class JvmDemo0 {

    public static void main(String[] args) {
        List<Integer[]> list = new ArrayList<>(1000000);
        try {
            for (int i = 0; i < 1000000; i++) {
                Integer[] ivals = new Integer[1024];
                list.add(ivals);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            System.out.println(list.size());


            FutureTask<Object> task = new FutureTask<>(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return null;
                }
            });
        }
    }
}
