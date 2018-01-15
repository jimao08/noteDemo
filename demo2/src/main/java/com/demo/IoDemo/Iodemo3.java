package com.demo.IoDemo;

import java.io.*;
import java.util.HashMap;

public class Iodemo3 {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("tt");

//        ByteArrayInputStream bis = new ByteArrayInputStream("hahah".getBytes());

//        SequenceInputStream sequenceInputStream = new SequenceInputStream(fileInputStream, bis);

        int read = 0;
        byte[] bytes = new byte[64];
        while ((read = fileInputStream.read(bytes)) != -1) {
            System.out.println(read);
            System.out.println(new String(bytes, 0, read));
        }



    }
}
