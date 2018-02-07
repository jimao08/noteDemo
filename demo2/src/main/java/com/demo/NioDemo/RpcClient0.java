package com.demo.NioDemo;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RpcClient0 {

    private static SocketChannel channel;

    public RpcClient0() {

    }

    private void connect(InetSocketAddress address) throws IOException {
        channel = SocketChannel.open();

        channel.configureBlocking(true);

        channel.connect(address);

        System.out.println("connect server:" + channel.getRemoteAddress());
    }

    public void close() throws Exception {
        if (channel.isOpen()) {
            channel.close();
        }
    }

    public <T> T create(Class<T> className) throws Exception {
        if (!className.isInterface()) {
            throw new IllegalArgumentException();
        }

        String serviceName = className.getSimpleName();
        InetSocketAddress serverAdress = RpcServerRegister0.discover(serviceName);

        if (serverAdress == null) {
            throw new Exception("no service " + serviceName);
        }

        if (channel == null) {
            connect(serverAdress);
        } else  {
            InetSocketAddress old = (InetSocketAddress) channel.getRemoteAddress();
            System.out.println(old);

            if (!serverAdress.getHostString().equals(old.getHostString()) || serverAdress.getPort() != old.getPort()) {
                channel.close();
                connect(serverAdress);
            }
        }


        ClassLoader classLoader = className.getClassLoader();
        Object o = Proxy.newProxyInstance(classLoader, new Class[]{className}, new MyHandler(className, this));
        return ((T) o);
    }

    public Object invoke(Class className, Method method, Object[] param) throws Exception {
        RpcMethod rpcMethod = new RpcMethod(className.getSimpleName(),
                method.getName(), param);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(rpcMethod);

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
