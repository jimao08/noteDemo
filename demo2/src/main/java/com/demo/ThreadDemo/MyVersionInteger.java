package com.demo.ThreadDemo;

import java.util.concurrent.atomic.AtomicInteger;

public class MyVersionInteger {

    private volatile int value;
    private volatile AtomicInteger version = new AtomicInteger(0);

    public MyVersionInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getVersion() {
        return version.get();
    }

    public boolean set(int val, int ver) {
        if (version.get() == ver) {
//            System.out.println("value=" + ver + ">" + val);
            version.incrementAndGet();
            this.value = val;
            return true;
        }
        return false;
    }

    public int getAndIncrement() {
        return 0;
    }
}
