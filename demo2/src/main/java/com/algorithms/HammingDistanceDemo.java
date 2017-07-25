package com.algorithms;

/**
 * Created by myle on 2017/07/26 00:58
 *
 * description : https://leetcode.com/problems/hamming-distance/#/description
 */
public class HammingDistanceDemo {
    public static void main(String[] args) {
        Integer val1 = 137;
        Integer val2 = 177;

        String s1 = Integer.toBinaryString(val1);
        String s2 = Integer.toBinaryString(val2);


        System.out.println(s1);
        System.out.println(s2);

        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s2.charAt(i) != s1.charAt(i)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
