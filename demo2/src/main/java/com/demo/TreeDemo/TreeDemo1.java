package com.demo.TreeDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by myle on 2018/01/28 下午 14:59
 */

public class TreeDemo1 {

    public static void main(String[] args) {
        MyBinaryTree tree = new MyBinaryTree();

        Random random = new Random(110);

        ArrayList list = new ArrayList();

        for (int i = 0; i < 50000000; i++) {
            list.add(random.nextInt(100000));
        }

        long stime = System.currentTimeMillis();
        Collections.sort(list);
        System.out.println(Collections.binarySearch(list,-1));

        System.out.println("utime=" + (System.currentTimeMillis() - stime));

    }
}
