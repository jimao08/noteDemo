package com.demo.IoDemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;

/**
 * nio测试
 * Created by linkang on 10/11/16.
 */
public class Iodemo1 {
    public static void main(String[] args) {
        try {
            File file = new File("1.txt");
            RandomAccessFile aFile = new RandomAccessFile(file, "rw");

            FileChannel inChannel = aFile.getChannel();
            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //read into buffer.
            int count = 1;
//            buf.clear();
//            buf.put("nba game".getBytes());
//            inChannel.write(buf);
//            inChannel.write(buf);
            while (bytesRead != -1) {
                System.out.println(count++);
                buf.flip();  //make buffer ready for read

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                System.out.println();
                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);

            }
            aFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
