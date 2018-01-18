package com.demo.JvmDemo;

import java.util.ArrayList;
import java.util.List;

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
        }
    }
}
