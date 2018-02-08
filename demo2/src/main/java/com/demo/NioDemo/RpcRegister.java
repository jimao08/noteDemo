package com.demo.NioDemo;

import java.net.InetSocketAddress;

public interface RpcRegister {

    void register(InetSocketAddress address, Class aClass) throws Exception;

    InetSocketAddress discover(String serviceName) throws Exception;

    void unregister(InetSocketAddress address,Class aClass) throws Exception ;

}
