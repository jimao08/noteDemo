package com.demo.ThreadDemo;

public interface MyAtomicInteger {

    int get();

    int incrementAndGet();

    int getAndIncrement();
}
