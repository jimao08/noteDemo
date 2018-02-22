package com.demo.ThreadDemo;

import sun.misc.Unsafe;

public class MyCASInteger implements MyAtomicInteger {

    private volatile int value;

    private static Unsafe unsafe;

    private static final long OFFSET;

    static {
        try {
            Class<MyCASInteger> aClass = MyCASInteger.class;
            unsafe = MyUnsafe.getUnsafe();
            OFFSET = unsafe.objectFieldOffset(aClass.getDeclaredField("value"));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public MyCASInteger() {
    }

    public MyCASInteger(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    public int incrementAndGet() {
        while (true) {
            int old = value;
            int newValue = old + 1;
            if (compareAndSet(old, newValue)) {
                return value;
            }
            System.out.println("set fail.");
        }
    }

    public int getAndIncrement() {
        while (true) {
            int old = value;
            int newValue = old + 1;
            if (compareAndSet(old, newValue)) {
                return old;
            }
        }
    }

    private boolean compareAndSet(final int expect, int newValue) {
        return unsafe.compareAndSwapInt(this, OFFSET, expect, newValue);
    }

    private boolean myCompareAndSet(final int expect, int newValue) {

        //没有锁，并发情况下问题出现在这里，判断和赋值发生顺序有先后
        if (value == expect) {
            value = newValue;
            return true;
        }
        return false;
    }
}
