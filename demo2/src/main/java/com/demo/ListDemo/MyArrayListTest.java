package com.demo.ListDemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by myle on 2018/01/27 下午 16:06
 */

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>(80);


        for (int i = 0; i < 10; i++) {
            list.add("AAA" + i);
        }

        list.set(0, "bbbb");
        System.out.println(list.get(0));

        System.out.println(list.get(list.size() - 1));

        System.out.println(list.contains("AAA0"));

        System.out.println(list.toString());


        list.remove(9);

        list.remove(0);

        list.remove("AAA8");
        System.out.println(list);

        list.add(7, "aaaa");
        System.out.println(list);

        List list1 = new LinkedList();
        list1.add(0,"object");


        int[] ivals = {1, 2, 3, 4, 5, 6,7};
        System.arraycopy(ivals, 3, ivals, 4, 3);

        for (int i = 0; i < ivals.length; i++) {
            System.out.println(ivals[i]);
        }
    }
}
