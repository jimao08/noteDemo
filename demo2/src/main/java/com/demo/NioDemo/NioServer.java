package com.demo.NioDemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioServer {
    private static volatile boolean shutdown = false;
    private static Thread s;

    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                int ival = 1;
                while (true) {
                    ival++;
                    Thread.yield();
                }
            }
        }).start();

        s = Thread.currentThread();

        ServerSocket serverSocket = new ServerSocket(8889);

        while (!shutdown) {
            System.out.println("wait.");
            Socket accept = serverSocket.accept();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SocketChannel channel = accept.getChannel();

//                        InputStream inputStream = accept.getInputStream();
//
//                        InputStreamReader reader = new InputStreamReader(inputStream);
//
//                        BufferedReader reader1 = new BufferedReader(reader);
//
//
//                        String line = reader1.readLine();
//
//                        if (line.equals("shutdown")) {
//                            shutdown = true;
//                        }
//                        System.out.println(line);
//
//                        reader.close();

                        ByteBuffer bf = ByteBuffer.allocate(40);

                        if (channel == null) {
                            System.out.println("null channel.");
                        }

                        while (channel.read(bf) != -1) {
                            bf.flip();

                            while (bf.hasRemaining()) {
                                System.out.print(bf.get());
                            }
                            bf.compact();
                        }

                        accept.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();

        }

        serverSocket.close();
    }
}
