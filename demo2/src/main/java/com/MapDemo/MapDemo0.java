package com.MapDemo;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by linkang on 2017/11/02 上午11:26
 */
public class MapDemo0 {

    public static void main(String[] args) throws Exception{


        HashMap<Integer, String> map = new HashMap<>(31);

        for (int i = 0; i < 24; i++) {
            map.put(i, i+"");
        }


        map.put(null, "a");

        map.get(null);

        map.put(123, "dsdfsdd");

        map.put(201, "dsdfsdd");

        map.get(123);

        System.out.println(map);

        Set<Integer> keySet = map.keySet();

        map.isEmpty();

        map.size();

        map.containsKey(12);

        map.containsValue("abc");


        map.remove(12);


        HashMap<Integer,String> map2 = new HashMap();


        map2.put(222, "tes");
        map.putAll(map2);


        map2.clear();

        map.keySet();

        map.keySet();

        map.values();

        map.entrySet();
    }
}
