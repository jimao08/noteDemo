package com.demo;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linkang on 10/9/16.
 */
public class Demo1 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://pic2.zhimg.com/230337f78bd95dbc6cfdf91bc4048f3d_b.jpg");
            InputStream inputStream = url.openStream();

            File file = new File("test1.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            int tmp = 0;
            byte[] buffer = new byte[64];
            while ((tmp = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, tmp);
            }
            fileOutputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
