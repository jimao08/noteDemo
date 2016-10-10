package com.demo.NetworkDemo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created by linkang on 10/9/16.
 */
public class SocketDemo1 {
    public static void main(String[] args) throws Exception{
        Socket socket = null;
        try {
            socket = new Socket();
            SocketAddress sa = new InetSocketAddress("localhost",9000);
            socket.connect(sa);
            System.out.println(socket.getInetAddress().getHostAddress());
            System.out.println(socket.getLocalAddress().getHostAddress());

            String input = new Scanner(System.in).next();

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(input);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

    }
}
