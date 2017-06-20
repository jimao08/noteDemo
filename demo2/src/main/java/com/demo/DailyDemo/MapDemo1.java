package com.demo.DailyDemo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by linkang on 17-6-19.
 */
public class MapDemo1 {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16,0.75f,true);

        map.put("111", 111);
        map.put("222", 222);
        map.put("333", 333);
        map.put("444", 444);
        map.put("555", 555);
        map.put("666", 666);


        System.out.println(map.get("555"));

        System.out.println(map);
        System.out.println(map);


        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


        Integer ival1 = 100;
        Integer ival2 = 100;

        System.out.println(ival1 == ival2);
    }

}
