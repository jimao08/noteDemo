package com.demo.LambdaDemo;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * flatmap 扁平化
 * Created by linkang on 1/3/17.
 */
public class Lambda4 {
    public static void main(String[] args) {
        Integer[] integers1 = {1, 2, 3};
        Integer[] integers2 = {3, 4};


//        ArrayList<Integer[]> list= new ArrayList<>();

//        for (int i = 0; i < integers1.length; i++) {
//            for (int j = 0; j < integers2.length; j++) {
//                Integer[] ints = new Integer[2];
//                ints[0] = integers1[i];
//                ints[1] = integers2[j];
//                list.add(ints);
//            }
//        }


//        List<Object[]> list1 = Arrays.stream(integers1)
//                .map(i -> Arrays.stream(integers2)
//                        .map(j -> new Integer[]{i, j})
//                        .toArray())
//                .collect(Collectors.toList());

        List<Integer[]> list = Arrays.stream(integers1)
                .flatMap(i -> Arrays.stream(integers2)
                        .filter(k -> (i + k) % 3 == 0)
                        .map(j -> new Integer[]{i, j}))

                .collect(Collectors.toList());

//        Integer[] values = (Integer[]) list1.get(2)[0];
//        System.out.println(values[0]);


        for (Integer[] ints : list) {
            System.out.println(StringUtils.join(ints,","));
        }

    }
}
