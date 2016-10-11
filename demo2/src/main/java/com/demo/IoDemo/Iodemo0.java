package com.demo.IoDemo;

import java.io.*;

/**
 * mark reset的用法
 * Created by linkang on 10/11/16.
 */
public class Iodemo0 {

    public static void main(String[] args) {
        try {
            File file = new File("1.txt");
            InputStream is = new FileInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));


            reader.mark(1024);
            String line = reader.readLine();

            reader.reset();
            String line2 = reader.readLine();

            reader.reset();
            String line3 = reader.readLine();

            System.out.println(line3);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
