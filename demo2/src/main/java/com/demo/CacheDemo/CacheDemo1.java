package com.demo.CacheDemo;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by linkang on 10/27/16.
 */
public class CacheDemo1 {
    private static Cache<String, String> cache = CacheBuilder.newBuilder().initialCapacity(5)
            .maximumSize(10)
            .expireAfterWrite(5, TimeUnit.SECONDS).build();

    public static void main(String[] args) throws InterruptedException {
        cache.put("d", "dd");

        Thread.sleep(6000);

        String value = cache.getIfPresent("d");
        System.out.println(value + "");
    }
}
