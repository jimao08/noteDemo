package com.demo.NioDemo;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class NioDemo0 {


    public static void main(String[] args) throws Exception {
        File file = new File("test01");

//        FileInputStream fileInputStream = new FileInputStream(file);
//        FileChannel channel = fileInputStream.getChannel();
//
//
//        ByteBuffer bf = ByteBuffer.allocate(64);
//
//
//        int read = 0;
//        while ((read = channel.read(bf)) != -1) {
//            bf.flip();
//
//            while (bf.hasRemaining()) {
//                System.out.print((char) bf.get());
//            }
//
//            bf.compact();
//
//            System.out.println(bf.remaining());
//        }

        final String s = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\workspace\\noteDemo\\demo2\\pom.xml"));


        FileOutputStream fos = new FileOutputStream(file);
        FileChannel channel = fos.getChannel();

        ByteBuffer bf = ByteBuffer.allocate(10);


        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
//            text.append(s);
        }

        byte[] bytes = "hello".getBytes();
        System.out.println(bytes.length);

        long stime = System.currentTimeMillis();
//        for (byte b : bytes) {
//            bf.put(b);
//            bf.flip();
//            channel.write(bf);
//            bf.clear();
//        }


        bf = ByteBuffer.wrap(bytes);
        bytes[0] = 'x';

        int count = 0;
        while (bf.hasRemaining()) {
            channel.write(bf);
            count++;
        }

        System.out.println(count);

        channel.close();
        fos.flush();
        fos.close();


        System.out.println("utime=" + (System.currentTimeMillis() - stime));

    }
}
