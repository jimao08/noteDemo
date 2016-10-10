package com.demo.NetworkDemo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Junit
 * Created by linkang on 10/10/16.
 */

public class JunitDemo {
    public static void main(String[] args) {
        System.out.println(0x21afL);
    }

    @Before
    public void aaa() {
        System.out.println("before");
    }


    @Test
    public void dd() {
        double currentTime = System.currentTimeMillis();
        Assert.assertTrue(currentTime > 0 );
    }

    @After
    public void ccc() {
        System.out.println("this is after");
    }
}
