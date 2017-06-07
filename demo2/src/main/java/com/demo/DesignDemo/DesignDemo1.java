package com.demo.DesignDemo;

import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 *
 * Created by linkang on 17-6-6.
 */
public class DesignDemo1 {

    public static void main(String args[]) {
        char[] cbuf = new char[256];
        System.out.println("hey, may I have your name, please? ");
        int n = 0;

        //InputStreamReader使用了适配器模式,将InputStream转为Reader
//        Reader r = new InputStreamReader(System.in);


        //BufferedReader装饰器模式
        Reader r = new BufferedReader(new InputStreamReader(System.in));


        StringReader stringReader = new StringReader("bafasd");

        BufferedReader bufferedReader = new BufferedReader(stringReader);

        System.out.println();


        try {
            n = r.read(cbuf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hello, Mr. " + cbuf[0]);

    }
}
