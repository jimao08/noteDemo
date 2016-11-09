package com.demo.LambdaDemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by linkang on 10/19/16.
 */
public class Lambda1 {
    public static void main(String[] args) {
        String[] strings = {"12342", "abc", "12erf"};
        List<String> list = Arrays.asList(strings);


        Integer[] ints = {12, 3, 43, 4, 34, 5, 6};
        Arrays.stream(ints).map(ival -> ival++);
        Arrays.stream(ints).forEach(System.out::println);

        List<Integer> list1 = Arrays.stream(ints).filter(value -> value > 10).collect(Collectors.toList());



        System.out.println(list1);

    }
}
