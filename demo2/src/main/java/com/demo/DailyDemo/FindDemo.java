package com.demo.DailyDemo;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by linkang on 17-6-16.
 */
public class FindDemo {


    public static void main(String[] args) {
        int[] testValues = {12, 3, 4, 56, 100, 200};

        Arrays.sort(testValues);

        System.out.println(StringUtils.join(testValues, ','));


        System.out.println(find3(testValues, 200));


        ArrayList<Integer> list = new ArrayList<>();
        for (int testValue : testValues) {
            list.add(testValue);
        }

        System.out.println(Collections.binarySearch(list, 200));


        LinkedList<Integer> list2 = new LinkedList<>(list);
        ListIterator<Integer> iterator = list2.listIterator();


        System.out.println(get(iterator, 5));

        System.out.println(get(iterator, 2));

    }

    private static <T> T get(ListIterator<? extends T> i, int index) {
        T obj = null;
        int pos = i.nextIndex();
        if (pos <= index) {
            do {
                System.out.println("pos=" + pos + ">>>" + "index=" + index);
                obj = i.next();
            } while (pos++ < index);
        } else {
            do {
                System.out.println("pos=" + pos + "<<<" + "index=" + index);
                obj = i.previous();
            } while (--pos > index);
        }
        return obj;
    }


    /**
     * 二分查找
     *
     * @param list
     * @param search
     * @return
     */
    public static int find(int[] list, int search) {

        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("mid=" + mid);

            if (list[mid] < search) {
                low = mid + 1;
            } else if (list[mid] > search) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }


    public static int find3(int[] list, int search) {

        int low = 0;
        int high = list.length;

        while (low < high) {
            int mid = (low + high) / 2;
            System.out.println("mid=" + mid);

            if (list[mid] < search) {
                low = mid;
            } else if (list[mid] > search) {
                high = mid;
            } else {
                return mid;
            }
        }

        return -1;
    }


    /**
     * 递归二分查找
     *
     * @param list
     * @param search 查找值
     * @return
     */
    public static int find2(int[] list, int search) {
        return findMethod(list, 0, list.length, search);
    }


    public static int findMethod(int[] list, int low, int high, int search) {
        //结束条件
        if (low >= high) {
            return -1;
        }

        int mid = (low + high) / 2;
        System.out.println("mid=" + mid);
        if (list[mid] > search) {
            return findMethod(list, low, mid, search);
        } else if (list[mid] < search) {
            return findMethod(list, mid, high, search);
        } else {
            return mid;
        }
    }


}
