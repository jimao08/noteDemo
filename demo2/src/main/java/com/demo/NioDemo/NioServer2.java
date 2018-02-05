package com.demo.NioDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer2 {
    private static volatile boolean shutdown = false;


    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8889));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int select = selector.select();
            System.out.println(select);


            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    System.out.println("accept");
                    handleAccept(key);
                } else if (key.isReadable()) {
                    System.out.println("readable");
                    handleRead(key);
                } else if (key.isValid() && key.isWritable()) {
                    System.out.println("writable");
                    handleWrite(key);

                } else if (key.isConnectable()) {
                    System.out.println("isConnectable");
                }

                iterator.remove();
            }
        }
    }


    public static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);

        socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(64));
    }


    public static void handleRead(SelectionKey key) throws IOException {


        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();

        int read = socketChannel.read(buffer);

        while (read > 0) {
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }

            buffer.clear();
            read = socketChannel.read(buffer);
        }

        socketChannel.register(key.selector(), SelectionKey.OP_WRITE, ByteBuffer.allocate(64));
    }

    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer bf = (ByteBuffer) key.attachment();

        SocketChannel channel = (SocketChannel) key.channel();

        bf.put("server".getBytes());
        bf.flip();
        channel.write(bf);
        bf.compact();


        channel.close();
    }
}
