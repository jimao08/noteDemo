package com.demo.LambdaDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by linkang on 12/28/16.
 */
public class Lambda3 {
    public static void main(String[] args) {
        Integer[] ints = {3, -43, 4, -34, 5, 6,7,8,100,1000,10001};
        List<Integer> tmp = Arrays.asList(ints);
        ArrayList<Integer> list = new ArrayList<>(tmp);

        int count = 0;

//        Iterator<Integer> iterator = list.iterator();

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("i=" + i + ">>value=" + list.get(i));
//            if (list.get(i) < 0) {
//                list.remove(i);
//            }
//            count++;
//        }

//        List<Integer> list1 = list.stream().filter(i -> i > 0).collect(Collectors.toList());
//        while (iterator.hasNext()) {
//            Integer ival = iterator.next();
//            if (ival < 0) {
//                ival += 100;
//            }
//        }



//        list.removeIf(i -> i < 0);
//        List<Integer> list1= Collections.unmodifiableList(list);
//        list1.set(0, 111);


//        System.out.println(list1);

//        list.
//        System.out.println(result);


        List<Integer> result1 = list.stream().map(i -> i * i).collect(Collectors.toList());
        System.out.println(result1);
    }
}
