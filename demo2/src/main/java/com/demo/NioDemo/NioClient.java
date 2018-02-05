package com.demo.NioDemo;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NioClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8889);
        OutputStream os = socket.getOutputStream();

        os.write("hehe".getBytes());

        os.flush();

        InputStream inputStream = socket.getInputStream();

        byte[] bytes = new byte[48];
        int read ;
        while ((read = inputStream.read(bytes))!= - 1) {

            System.out.println(new String(bytes, 0, read));
        }


        socket.close();
    }
}
