package com.demo.NioDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer2 {
    private static volatile boolean shutdown = false;



    public static void main(String[] args) throws Exception{

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
                }

                if (key.isReadable()) {
                    System.out.println("readable");
                }

                if (key.isWritable() && key.isValid()) {
                    System.out.println("writable");
                }

                if (key.isConnectable()) {
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



}
