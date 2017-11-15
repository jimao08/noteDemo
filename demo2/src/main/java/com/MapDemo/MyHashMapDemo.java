package com.MapDemo;

public class MyHashMapDemo {


    public static void main(String[] args) {

        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();

        for (int i = 0; i < 12; i++) {
            myHashMap.put(i, 231);
        }

        myHashMap.put(123, 333);

        System.out.println(myHashMap.get(1));
    }
}
