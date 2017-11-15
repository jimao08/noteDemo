package com.demo.ThreadDemo;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by linkang on 2017/11/15 上午10:46
 */
public class ThreadDemo6 {
    public static void main(String[] args) throws Exception{
        Integer[] ivals = new Integer[10];

        Integer testObj = new Integer(910);
        MyUnsafe.getUnsafe().compareAndSwapObject(ivals, 16, null, testObj);
        MyUnsafe.getUnsafe().compareAndSwapObject(ivals, 16, testObj, 920);

        System.out.println(StringUtils.join(ivals, ','));

    }
}
