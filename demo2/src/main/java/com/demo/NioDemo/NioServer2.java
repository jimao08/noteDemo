package com.demo.NioDemo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.stream.Collectors;

public class NioServer2 {
    private static volatile boolean shutdown = false;

    private static Map<String, Object> testMap = new HashMap<>();


    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, null);

        serverSocketChannel.bind(new InetSocketAddress(8889));
        System.out.println("nio server start.");

        while (true) {

            int select = selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                if (next.isAcceptable()) {
                    SocketChannel accept = serverSocketChannel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(40));
                } else if (next.isReadable()) {
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer buffer = (ByteBuffer) next.attachment();

                    int read = channel.read(buffer);
                    int last = buffer.position() - 1;
                    if (read == -1 || last < 0) {

                        if (channel.isOpen()) {
                            System.out.println(channel.getRemoteAddress() + ":close");
                            channel.close();
                        }
                        continue;
                    }

                    char ch;
                    if ((ch = (char) buffer.get(last)) != '\n') {
                        continue;
                    }

                    buffer.flip();
                    String line = new String(buffer.array(), 0, buffer.limit()).trim();

                    System.out.println(channel.getRemoteAddress() + ":" + line);

                    for (String command : line.split("\n")) {
                        handleCommand(selector, channel, command);
                    }


                    buffer.clear();

                } else if (next.isWritable()) {
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer buffer = (ByteBuffer) next.attachment();

                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        channel.write(buffer);
                    }
//                    next.interestOps(SelectionKey.OP_READ);

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

        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        writeBuffer = null;

//        socketChannel.register(selector, SelectionKey.OP_WRITE, writeBuffer);
    }

    private static void handleCommand(Selector selector, SocketChannel channel, String command) throws Exception {
        if (command == null || command.trim().equals("")) {
            return;
        }

        if ("quit".equals(command.trim())) {
            System.out.println("do quit");
            channel.close();
        } else if (command.startsWith("set")) {
            List<String> params = getParams(command, "set");
            if (params.size() == 2) {
                testMap.put(params.get(0), params.get(1));
            }
        } else if (command.startsWith("get")) {
            List<String> params = getParams(command, "get");
            if (params.size() == 1) {
                String result = testMap.get(params.get(0)) + "";
                System.out.println(result);

                writeResponse(selector, channel, result);
            }

        } else if (command.startsWith("keys")) {

            List<String> params = getParams(command, "keys");
            if (params.size() == 1) {
                String pattern = params.get(0).replaceAll("\\*", ".*");
                Set<String> keySet = testMap.keySet().stream()
                        .filter(i -> i.matches(pattern)).collect(Collectors.toSet());
                writeResponse(selector, channel, keySet.toString());
            }

        } else if (command.startsWith("hset")) {

            List<String> params = getParams(command, "hset");
            if (params.size() == 3) {

                String k = params.get(0);
                String hashKey = params.get(1);
                String value = params.get(2);

                Object o = testMap.get(k);
                if (o == null) {
                    o = new HashMap<String, Object>();
                    testMap.put(k, o);
                }

                HashMap<String, Object> map = (HashMap<String, Object>) o;
                map.put(hashKey, value);
            }
        } else if (command.startsWith("hget")) {

            List<String> params = getParams(command, "hget");

            if (params.size() == 2) {
                String k = params.get(0);
                String hashKey = params.get(1);

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
        } else {
            System.out.println("command=" + command);
        }
    }

    private static List<String> getParams(String command, String start) {
        String[] split = command.split(" ");

        List<String> params = new ArrayList<>();
        for (String str : split) {
            if (str != null && !str.trim().equals("") && !str.trim().equals(start)) {
                params.add(str.trim());
            }
        }

        return params;
    }

}