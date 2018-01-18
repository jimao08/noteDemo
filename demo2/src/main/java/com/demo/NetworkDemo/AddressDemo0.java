package com.demo.NetworkDemo;

import java.net.InetAddress;
import java.util.HashMap;

/**
 * 测试address
 * adress demo
 * Created by linkang on 10/9/16.
 */
public class AddressDemo0 {

    public static void main(String[] args) {
        try {
            HashMap<String, Integer> m = new HashMap<>();
            for (int i = 0; i < 1000000; i++) {
                InetAddress address = InetAddress.getByName("www.baidu.com");
                String add = address.getHostAddress();
                if (m.containsKey(add)) {
                    m.put(add,m.get(add) + 1);
                } else {
                    m.put(add, 1);
                }

                if (i % 1000 == 0) {
                    Thread.sleep(100);
                }
            }

            for (String key : m.keySet()) {
                System.out.println(key + ">>>" + m.get(key));
            }

            //结果有两个ip
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
