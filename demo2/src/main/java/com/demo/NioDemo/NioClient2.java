package com.demo.NioDemo;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient2 {

    public static void main(String[] args) throws Exception {
//        Socket socket = new Socket("localhost", 8889);


        for (int i = 0; i < 10; i++) {
            final int ival = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        SocketChannel sc = SocketChannel.open();
                        sc.configureBlocking(false);


                        sc.connect(new InetSocketAddress("localhost", 8889));

                        ByteBuffer bf = ByteBuffer.allocate(64);


//                        while (!sc.finishConnect()) {
//                            Thread.yield();
//                        }

                        if (sc.finishConnect()) {
                            bf.clear();
                            bf.put(("hello world" + ival).getBytes());
                            bf.flip();

                            while (bf.hasRemaining()) {
                                sc.write(bf);
                            }
                        }
                        System.out.println("finish " + ival + ">" + sc.isConnected());

                        sc.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
