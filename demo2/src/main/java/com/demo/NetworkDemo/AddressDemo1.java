package com.demo.NetworkDemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 测试socket address
 * Created by linkang on 10/10/16.
 */
public class AddressDemo1 {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress.getHostAddress());
            System.out.println(inetAddress.getHostName());

            System.out.println();

            InetAddress[] inetAddresses = InetAddress.getAllByName("gw.api.taobao.com");

            for (InetAddress address : inetAddresses) {
                System.out.println(address.getHostAddress());
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
