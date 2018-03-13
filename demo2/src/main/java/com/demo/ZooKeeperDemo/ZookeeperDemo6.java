package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.server.LogFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;

public class ZookeeperDemo6 {


    public static void main(String[] args) throws Exception {
        File path = new File("C:\\tmp\\zookeeper\\version-2");

        for (File file : path.listFiles()) {
            LogFormatter.main(new String[]{file.getPath()});
        }

        File file = new File("zk/zoo.cfg");

        FileInputStream fileInputStream = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInputStream);


        fileInputStream.close();

        String dataDir = (String)properties.get("dataDir");

        System.out.println(dataDir);


        System.out.println(properties);


    }
}
