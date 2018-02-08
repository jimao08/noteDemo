package com.demo.NioDemo;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用文件来记录注册信息
 */
public class RpcFileRegister0 implements RpcRegister {

    private static final String FILE_PATH = "register";
    private static Map<String, List<InetSocketAddress>> serviceAdressMap = new HashMap<>();
    private static Object lock = new Object();

    private static RpcFileRegister0 register;

    private RpcFileRegister0() {
    }

    public static RpcFileRegister0 getInstance() {
        if (register == null) {
            register = new RpcFileRegister0();
        }
        return register;
    }

    public void register(InetSocketAddress address, Class aClass) {
        synchronized (lock) {
            readMaps();

            String serviceName = aClass.getInterfaces()[0].getSimpleName();

            List<InetSocketAddress> addressList = serviceAdressMap.get(serviceName);
            if (addressList == null) {
                addressList = new ArrayList<>();
                serviceAdressMap.put(serviceName, addressList);
            }
            addressList.add(address);

            updateMaps();

            System.out.println("register:" + address + "," + serviceName + "," + aClass);
        }
    }

    public InetSocketAddress discover(String serviceName) {

        try {
            readMaps();
            return serviceAdressMap.get(serviceName).get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        register = null;
        serviceAdressMap = null;
    }

    private static void readMaps() {
        try {
            File file = new File("register");
            if (!file.exists()) {
                return;
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            serviceAdressMap = (HashMap<String, List<InetSocketAddress>>) ois.readObject();

            fileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void updateMaps() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(serviceAdressMap);

            fileOutputStream.close();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void unregister(InetSocketAddress address,Class aClass) {
        synchronized (lock) {
            readMaps();
            String serviceName = aClass.getInterfaces()[0].getSimpleName();
            List<InetSocketAddress> addressList = serviceAdressMap.get(serviceName);

            if (addressList != null) {
                addressList.remove(address);
            }

            updateMaps();
            System.out.println("unregister server:" + address);
        }
    }

}
