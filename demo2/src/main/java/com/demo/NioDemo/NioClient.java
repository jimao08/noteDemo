package com.demo.NioDemo;

import java.io.OutputStream;
import java.net.Socket;

public class NioClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8889);
        OutputStream os = socket.getOutputStream();

        os.write("hehe".getBytes());

        os.flush();
        socket.close();
    }
}
