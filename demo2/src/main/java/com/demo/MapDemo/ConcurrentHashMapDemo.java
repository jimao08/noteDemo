package com.demo.MapDemo;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class ConcurrentHashMapDemo {



    public static void main(String[] args) {

        ConcurrentHashMap<Integer,Object> map = new ConcurrentHashMap();

        for (int i = 0; i < 12; i++) {
            map.put(i, "value" + i);
        }

        map.get(11);

        map.size();
    }
}
