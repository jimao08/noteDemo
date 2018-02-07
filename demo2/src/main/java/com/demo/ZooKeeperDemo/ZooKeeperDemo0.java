package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

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

    }
}
