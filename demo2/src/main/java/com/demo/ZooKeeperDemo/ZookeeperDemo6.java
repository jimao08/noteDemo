package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.server.LogFormatter;
import org.apache.zookeeper.server.SnapshotFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ZookeeperDemo6 {


    public static void main(String[] args) throws Exception {
        File path = new File("C:\\tmp\\zookeeper\\log\\version-2\\");

        for (File file : path.listFiles()) {

            System.out.println("file:" + file.getCanonicalPath());
            
            LogFormatter.main(new String[]{file.getPath()});
        }

//        SnapshotFormatter.main(new String[]{path.getPath()});


        File file = new File("zk/zoo.cfg");

        FileInputStream fileInputStream = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInputStream);


        fileInputStream.close();

        String dataDir = properties.getProperty("dataDir");


        System.out.println(dataDir);


        System.out.println(properties);


        System.out.println(System.getProperty("ip", "0.0.0.0"));


        TreeSet<TestValue> testValues = new TreeSet<>(new Comparator<TestValue>() {
            @Override
            public int compare(TestValue o1, TestValue o2) {
                return o1.ival - o2.ival > 0 ? 1 : -1;
            }
        });
        testValues.add(new TestValue("nba", 124));
        testValues.add(new TestValue("aaanba", 124));


        System.out.println(testValues);

        char[] a = new char[1010];
        String astr = "";
        System.out.println('\u0020' == ' ');
        System.out.println(new String("\u0000").equals(""));

    }



    private static class TestValue {
        private String sval;
        private int ival;


        public TestValue(String sval, int ival) {
            this.sval = sval;
            this.ival = ival;
        }

        @Override
        public String toString() {
            return "TestValue{" +
                    "sval='" + sval + '\'' +
                    ", ival=" + ival +
                    '}';
        }
    }

}
