package com.demo.ThreadDemo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo0 {
    private int ival;
    public double dval;


    public static void main(String[] args) throws Exception{
        Unsafe unsafe = MyUnsafe.getUnsafe();


        for (Field field : UnsafeDemo0.class.getDeclaredFields()) {
            System.out.println(field.getName());
        }

        long offset = unsafe.objectFieldOffset(UnsafeDemo0.class.getDeclaredField("ival"));

        UnsafeDemo0 demo0 = new UnsafeDemo0();
        unsafe.compareAndSwapInt(demo0, offset, 0, -1200);
        System.out.println(demo0.ival);
    }
}
