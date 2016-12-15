package com.demo.LambdaDemo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by linkang on 12/6/16.
 */
public class Lambda2 {
    public static void main(String[] args) {
        Integer[] ints = {12, 3, 43, 4, 34, 5, 6,-199};

        int[] arrays = {1, 2, 3};

        List<Integer> integers = Arrays.asList(ints);

        System.out.println(integers.stream().map(i->1+i).collect(Collectors.toList()));

        System.out.println(new Date(1485860400000L));

        System.out.println(TimeUnit.HOURS.toMinutes(3));


    }
}


