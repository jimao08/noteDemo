package com.demo.NioDemo;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RpcServerRegister0 {

    static Map<String, Class> serviceClassMap = new HashMap<>();
    private static Map<String, List<InetSocketAddress>> serviceAdressMap = new HashMap<>();
    private static Object lock = new Object();

    public static void register(InetSocketAddress address, Class aClass) {
        synchronized (lock) {
            readMaps();

            String serviceName = aClass.getInterfaces()[0].getSimpleName();
            serviceClassMap.put(serviceName, aClass);

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

    public static InetSocketAddress discover(String serviceName) {

        try {
            readMaps();
            return serviceAdressMap.get(serviceName).get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static void readMaps() {
        try {
            File file = new File("register");
            if (!file.exists()) {
                return;
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            serviceClassMap = (Map<String, Class>) ois.readObject();
            serviceAdressMap = (HashMap<String, List<InetSocketAddress>>) ois.readObject();

            fileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void updateMaps() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("register");
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(serviceClassMap);
            oos.writeObject(serviceAdressMap);

            fileOutputStream.close();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void unregister(InetSocketAddress address,Class aClass) {
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
