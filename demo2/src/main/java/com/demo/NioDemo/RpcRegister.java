package com.demo.NioDemo;

import java.net.InetSocketAddress;

public interface RpcRegister {

    /**
     * server调用
     * 将提供的服务注册
     *
     * @param address
     * @param aClass service的具体实现类
     * @throws Exception
     */
    void register(InetSocketAddress address, Class aClass) throws Exception;

    /**
     * client调用
     * 返回服务器ip端口号
     *
     * @param serviceName
     * @return
     * @throws Exception
     */
    InetSocketAddress discover(String serviceName) throws Exception;

    /**
     *
     * @param address
     * @param aClass
     * @throws Exception
     */
    void unregister(InetSocketAddress address,Class aClass) throws Exception ;

}
