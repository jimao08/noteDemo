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


        System.out.println(getDifferenceCountByBit(val1,val2));

    }


    /**
     *
     * 试验
     * 数字转为字符串
     *
     * @param ival1
     * @param ival2
     * @return
     */
    public static int getDifferenceCountByString(int ival1,int ival2) {
        String s1 = Integer.toBinaryString(ival1);
        String s2 = Integer.toBinaryString(ival2);

        System.out.println(s1);
        System.out.println(s2);

        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s2.charAt(i) != s1.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    /**
     *
     * 进行异或运算，统计结果为1的个数
     *
     * @param ival1
     * @param ival2
     * @return
     */
    public static int getDifferenceCountByBit(int ival1,int ival2) {

        return Integer.bitCount(ival1 ^ ival2);
    }
}
