package com.demo.NetworkDemo;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class UrlDemo0 {


    public static void main(String[] args) throws Exception{

        InputStream inputStream = getData("https://pic2.zhimg.com/v2-9806b22cc6838996e89ec5c67dfd2203_b.jpg");


        FileOutputStream outputStream = new FileOutputStream("tt.jpg");

        byte[] buffer = new byte[64];

        int b;
        while ((b = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, b);
        }

        outputStream.flush();
        outputStream.close();

        inputStream.close();
    }

    private static InputStream getData(String url) throws Exception{
        URL urlObj = new URL(url);
        return urlObj.openStream();
    }

}
