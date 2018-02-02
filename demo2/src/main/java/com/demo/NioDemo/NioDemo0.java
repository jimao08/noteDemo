package com.demo.NioDemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioDemo0 {


    public static void main(String[] args) throws Exception {
        File file = new File("test01");

        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();


        ByteBuffer bf = ByteBuffer.allocate(64);


        int read = 0;
        while ((read = channel.read(bf)) != -1) {
            bf.flip();

            while (bf.hasRemaining()) {
                System.out.print((char) bf.get());
            }

            bf.compact();

            System.out.println(bf.remaining());
        }


    }
}
