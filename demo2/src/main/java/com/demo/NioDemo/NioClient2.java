package com.demo.NioDemo;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient2 {

    public static void main(String[] args) throws Exception {
//        Socket socket = new Socket("localhost", 8889);


        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);


        sc.connect(new InetSocketAddress("localhost", 8889));

        ByteBuffer bf = ByteBuffer.allocate(64);
        if (sc.finishConnect()) {
            bf.clear();
            bf.put("hello world".getBytes());
            bf.flip();

            while (bf.hasRemaining()) {
                sc.write(bf);
            }
        }

        sc.close();

    }
}
