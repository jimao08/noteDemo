package com.demo.IoDemo;

import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by linkang on 2017/10/30 下午4:53
 */
public class Iodemo2 {

    public static void main(String[] args) throws Exception{
        File file = new File("abc.test");


//        FileUtils.readLines(file);
        InputStream inputStream = new FileInputStream(file);


        int read = 0;
//        byte[] buffer = new byte[64];

//        StringBuilder sb = new StringBuilder();
//        while ((read = inputStream.read()) != -1) {
//            System.out.println(read);
//        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);


        String line2 = null;
        while ((line2 = bufferedReader.readLine()) != null) {
            System.out.println("line=" + line2);
        }


//        String line = readLine(inputStream);
//        System.out.println(line);

    }


    public static String readLine(InputStream inputSteam) throws Exception{
        String line = "";
        int read = -1;
        while ((read = inputSteam.read()) != '\n') {
            line += ((char) read);
        }
        return line;
    }
}
