package com.demo.NioDemo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class NioServer2 {
    private static volatile boolean shutdown = false;

    private static Map<String, Object> testMap = new HashMap<>();


    public static void main(String[] args) throws Exception {
        System.out.println("nio server start.");
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null);

        serverSocketChannel.bind(new InetSocketAddress(8889));

        while (true) {

            int select = selector.select();
//            System.out.println("select=" + select);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                if (next.isAcceptable()) {
//                    System.out.println("isAcceptable");
                    SocketChannel accept = serverSocketChannel.accept();
                    accept.configureBlocking(false);
//                    accept.register(selector, SelectionKey.OP_CONNECT, ByteBuffer.allocate(40));
                    accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(40));
                } else if (next.isReadable()) {
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer buffer = (ByteBuffer) next.attachment();

                    int read = channel.read(buffer);

                    int last = buffer.position() - 1;
//                    if (last < 0) {
//                        continue;
//                    }



                    char ch;
                    if ((ch = (char) buffer.get(last)) != '\n') {
                        buffer.clear();
                        iterator.remove();
                        continue;
                    }

                    buffer.flip();
                    String command = new String(buffer.array(), 0, buffer.limit()).trim();

                    System.out.println(command);

                    handleCommand(selector, channel, command);

                    buffer.clear();

                } else if (next.isWritable()) {
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer buffer = (ByteBuffer) next.attachment();

                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        channel.write(buffer);
                    }
                    next.interestOps(SelectionKey.OP_READ);

                } else if (next.isConnectable()) {
                    System.out.println("isConnectable");
                }

                iterator.remove();
            }
        }
    }


    private static void writeResponse(Selector selector, SocketChannel socketChannel, String line) throws Exception {
        byte[] bytes = (line + "\n").getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        socketChannel.register(selector, SelectionKey.OP_WRITE, writeBuffer);
    }

    private static void handleCommand(Selector selector, SocketChannel channel, String command) throws Exception {
        if ("quit".equals(command.trim())) {
            channel.close();
        } else if (command.startsWith("set")) {
            String[] split = command.split(" ");

            if (split.length == 3) {
                testMap.put(split[1], split[2]);
            }
        } else if (command.startsWith("get")) {
            String[] split = command.split(" ");
            if (split.length == 2) {
                String result = testMap.get(split[1]) + "";
                System.out.println(result);

                writeResponse(selector, channel, result);
//                            writeBuffer.flip();
//                            channel.write(writeBuffer);
            }

        } else if (command.equals("keys *")) {
            Set<String> keySet = testMap.keySet();
            writeResponse(selector, channel, keySet.toString());
        } else if (command.startsWith("hset")) {
            String[] split = command.split(" ");

            if (split.length == 4) {

                String k = split[1];
                String hashKey = split[2];
                String value = split[3];

                Object o = testMap.get(k);
                if (o == null) {
                    o = new HashMap<String, Object>();
                    testMap.put(k, o);
                }

                HashMap<String, Object> map = (HashMap<String, Object>) o;
                map.put(hashKey, value);
            }
        } else if (command.startsWith("hget")) {
            String[] split = command.split(" ");

            if (split.length == 3) {

                String k = split[1];
                String hashKey = split[2];

                Object o = testMap.get(k);
                Object res = null + "";
                if (o != null) {
                    HashMap<String, Object> map = (HashMap<String, Object>) o;
                    res = map.get(hashKey);
                }
                writeResponse(selector, channel, res.toString());
            }
        } else if (command.equals("flushdb")) {
            testMap.clear();
        }
    }

}