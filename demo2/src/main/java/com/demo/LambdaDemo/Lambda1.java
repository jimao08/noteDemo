package com.demo.LambdaDemo;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by linkang on 10/19/16.
 */
public class Lambda1 {
    public static void main(String[] args) {
        String[] strings = {"12342", "abc", "12erf"};
        List<String> list = Arrays.asList(strings);


        Integer[] ints = {12, 3, 43, 4, 34, 5, 6,-199};
        List<Integer> list2 = Arrays.stream(ints).filter(i -> i > 0).map(ival -> ++ival).limit(2).sorted().collect(Collectors.toList());
        Arrays.stream(ints).forEach(System.out::println);
        System.out.println(list2);

        List<Integer> list1 = Arrays.stream(ints).filter(value -> value > 10).collect(Collectors.toList());

        int ival = 100_000_0100;
        System.out.println(ival);

        System.out.println(list1);
        LinkedList<AClass> list3 = new LinkedList<>();
        list3.add(new AClass(true, "hahah"));
        list3.add(new AClass(false, "bbbb"));
        list3.add(new AClass(true, "2123"));
        list3.add(new AClass(true, "3123"));
        list3.add(new AClass(true, "4123"));
        list3.add(new AClass(true, "5123"));
        list3.add(new AClass(true, "6123"));
        list3.add(new AClass(true, "aa7123"));


        List<String> list4 = list3
                .parallelStream()
                .skip(0)
                .filter(e->e.isA())
                .map(e -> e.getName())
                .collect(Collectors.toList());
        System.out.println(list4);

//        Arrays.stream(ints).iterate(0,i->i++).forEach(i -> System.out.print(i + "  "));

        File[] files = new File(".").listFiles(File::exists);
        Arrays.stream(files).forEach(i-> System.out.println(i.getName()));



        new Thread(() -> System.out.println("hello world")).start();


    }
}

class AClass {
    private boolean isA;
    private String name;

    public AClass(boolean isA, String name) {
        this.isA = isA;
        this.name = name;
    }

    public boolean isA() {
        return isA;
    }

    public void setA(boolean a) {
        isA = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
