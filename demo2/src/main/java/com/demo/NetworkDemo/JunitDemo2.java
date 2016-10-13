package com.demo.NetworkDemo;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Set;

/**
 * nio测试
 * Created by linkang on 10/11/16.
 */
public class JunitDemo2 {


    @Test
    public  void test1() {
        System.out.println(1<<0);
        System.out.println(SelectionKey.OP_ACCEPT);


        try {
            Selector selector = Selector.open();

            final String testString = "nba game";
            Set<SelectionKey> keys = selector.keys();
            for (SelectionKey key : keys) {
                SelectableChannel channel = key.channel();

                SocketChannel socketChannel = (SocketChannel) channel;

                if (key.isAcceptable()) {

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
