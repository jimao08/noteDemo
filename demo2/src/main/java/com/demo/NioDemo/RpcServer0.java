package com.demo.NioDemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class RpcServer0 {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(8887);
        serverSocketChannel.bind(address);

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("rpc server start.");

        RpcServerRegister0.register(address, HelloServiceImpl.class);
        RpcServerRegister0.register(address, TestServiceImpl.class);

        Thread hook = new Thread(new Runnable() {
            @Override
            public void run() {
                RpcServerRegister0.unregister(address, HelloServiceImpl.class);
                RpcServerRegister0.unregister(address, TestServiceImpl.class);
            }
        });
        hook.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(hook);

        while (true) {

            int select = selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();


                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("isAcceptable");
                } else if (key.isReadable()) {
                    System.out.println("read");
                    SocketChannel channel = null;
                    try {
                        channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();

                        if (!channel.isConnected() || !channel.isOpen()) {
                            continue;
                        }

                        int read = channel.read(buffer);

                        if (read == -1) {
                            channel.close();
                        }

                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, buffer.limit()));


                        RpcMethod method = readMethod(buffer.array());
                        Object o = invoke(method);
                        sendMessage(channel, o);

                        buffer.clear();
                    } catch (Exception ex) {
                        ex.printStackTrace();

                        if (channel != null && channel.isOpen()) {
                            channel.close();
                        }
                    }

                }

                iterator.remove();
            }
        }
    }

    private static RpcMethod readMethod(byte[] message) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(message);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object o = ois.readObject();

        RpcMethod method = (RpcMethod) o;

        ois.close();
        System.out.println("method=" + method);
        return method;
    }

    private static void sendMessage(SocketChannel channel, Object o) throws Exception {
        if (channel.isOpen()) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(o);
            oos.flush();
            byte[] message = bos.toByteArray();

            ByteBuffer buffer = ByteBuffer.allocate(message.length);

            buffer.put(message);
            buffer.flip();
            channel.write(buffer);

            oos.close();
        }
    }

    private static Object invoke(RpcMethod method) throws Exception {
        String serviceName = method.getServiceName();
        Class serviceClass = RpcServerRegister0.serviceClassMap.get(serviceName);
        if (serviceClass == null) {
            return null;
        }

        Object[] params = method.getParams();

        Class[] paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
        }

        Object obj = serviceClass.newInstance();

        Method m = serviceClass.getMethod(method.getMethodName(), paramTypes);

        if (m != null) {
            Object invoke = m.invoke(obj, params);
            return invoke;
        }

        return null;
    }
}
