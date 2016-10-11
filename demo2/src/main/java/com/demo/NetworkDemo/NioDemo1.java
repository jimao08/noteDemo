package com.demo.NetworkDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by linkang on 10/10/16.
 */
public class NioDemo1 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            ServerSocketChannel channel = serverSocket.getChannel();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
