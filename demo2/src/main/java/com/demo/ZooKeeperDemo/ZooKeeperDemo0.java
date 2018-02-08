package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ZooKeeperDemo0 {

    static CountDownLatch latch = new CountDownLatch(1);

    static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws Exception {

        long stime = System.currentTimeMillis();
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 120000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    semaphore.release();
                }
            }
        });

        semaphore.acquire();


        System.out.println(zooKeeper.getState().isConnected());
        System.out.println("utime=" + (System.currentTimeMillis() - stime));

//        zooKeeper.create("/e", "helloworld".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);


        Stat exists = zooKeeper.exists("/a", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeCreated) {
                    System.out.println("create node:" + event.getPath());
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                } else if (event.getType() == Event.EventType.NodeDeleted) {
                    System.out.println("del node:" + event.getPath());
                } else if (event.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("change");
                }
            }
        });

        if (exists != null) {
            System.out.println("version=" + exists.getVersion());
//            zooKeeper.delete("/a", exists.getVersion());

            Stat stat = zooKeeper.setData("/a", "bb".getBytes(), exists.getVersion());

            if (stat != null) {
                System.out.println("hll");
            }

        } else {
            String s = zooKeeper.create("/a", "helloworld".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(s);
        }

        zooKeeper.getData("/e", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("change");
                }
            }
        }, null);


        latch.await();
    }
}
