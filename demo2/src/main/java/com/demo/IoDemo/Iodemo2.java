package com.demo.IoDemo;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.lang.reflect.WildcardType;

/**
 * Created by linkang on 2017/10/30 下午4:53
 */
public class Iodemo2 {


    private static BufferedReader bufferedReader;

    public static void main(String[] args) throws Exception {
        File file = new File("abc.test");


//        FileUtils.readLines(file);
        InputStream inputStream = new FileInputStream(file);


        int read = 0;
//        byte[] buffer = new byte[64];

//        StringBuilder sb = new StringBuilder();
//        while ((read = inputStream.read()) != -1) {
//            System.out.println(read);
//        }

        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

//        String line2 = null;
//        line2 = bufferedReader.readLine();
//        System.out.println(line2);
//
//        line2 = bufferedReader.readLine();
//
//        System.out.println(line2);


//        while ((line2 = bufferedReader.readLine()) != null) {
//            System.out.println("line=" + line2);
//        }


//        String line = readLine(inputStream);
//        System.out.println(line);


        for (int i = 0; i < 10; i++) {
            new Thread(new ReadTask(bufferedReader)).start();
        }

    }


    public static String readLine(InputStream inputSteam) throws Exception {
        String line = "";
        int read = -1;
        while ((read = inputSteam.read()) != '\n') {
            line += ((char) read);
        }
        return line;
    }


    static class ReadTask implements Runnable {
        BufferedReader bufferedReader;

        public ReadTask(BufferedReader bufferedReader) {
            this.bufferedReader = bufferedReader;
        }

        @Override
        public void run() {
            try {
                String line = bufferedReader.readLine();

                if (line != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
