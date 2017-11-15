package com.demo.ThreadDemo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by linkang on 2017/11/15 上午10:38
 */
public class MyUnsafe {
    public static Unsafe getUnsafe() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        return unsafe;
    }
}
