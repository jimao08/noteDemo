package com.demo.NioDemo;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class RpcClient0 {

    private static SocketChannel channel;

    public RpcClient0() {
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws IOException {
        channel = SocketChannel.open();

        channel.configureBlocking(true);

        channel.connect(new InetSocketAddress("localhost", 8887));

        System.out.println("connect " + channel.getRemoteAddress());
    }

    public void close() throws Exception {
        if (channel.isOpen()) {
            channel.close();
        }
    }

    public HelloService create(Class className) throws Exception {
        return HelloServiceImpl.class.newInstance();
    }

    public Object invoke(Object param) throws Exception {
        //todo
        RpcMethod method = new RpcMethod("com.demo.NioDemo.HelloServiceImpl",
                "hello", new Object[]{param});

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(method);

        sendMessage(bos.toByteArray());

        return readMessage();
    }

    private void sendMessage(byte[] message) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(message.length);
        buffer.put(message);

        buffer.flip();
        channel.write(buffer);
    }

    private Object readMessage() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int read = channel.read(buffer);

        ByteArrayInputStream bis = new ByteArrayInputStream(buffer.array());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object o = ois.readObject();

        ois.close();
        return o;
    }
}
