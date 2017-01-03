package com.demo.LambdaDemo;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by linkang on 12/6/16.
 */
public class Lambda2 {
    public static void main(String[] args) {
        Integer[] ints = {12, 3, 43, 4, 34, 5, 6,-199,-300,-400};

        int[] arrays = {1, 2, 3};

        List<Integer> integers = Arrays.asList(ints);

//        System.out.println(integers.stream().map(i->1+i).collect(Collectors.toList()));
//
//
//        for (int j = 0; j < 10; j++) {
//
//            System.out.println(integers.stream().filter(i -> i < 0).findAny().get());
//        }




        System.out.println(integers);

//        integers = integers.stream().map(i->i+200).collect(Collectors.toList());

        integers.stream().forEach(i->i=200);
        System.out.println(integers);


        System.out.println(StringUtils.join(integers,">>"));


        ArrayList<AAA> list1 = new ArrayList<>();
        AAA a1 = new AAA();
        a1.setName("aaa");
        a1.setStatus(1);

        AAA a2 = new AAA();
        a2.setName("aaa");
        a2.setStatus(1);

        list1.add(a1);
        list1.add(a2);

        list1.stream().forEach(i->i.setStatus(-1));

        System.out.println(list1);

    }
}

class AAA {
    int status = 0;
    String name = "";

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AAA{" +
                "status=" + status +
                ", name='" + name + '\'' +
                '}';
    }
}


