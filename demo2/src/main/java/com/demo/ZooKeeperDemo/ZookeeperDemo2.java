package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.LogFormatter;
import org.apache.zookeeper.server.SnapshotFormatter;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDemo2 {

    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static final String THREE_SERVER_CONNECT_STRING = "localhost:2181,localhost:2182,localhost:2183";
    private static final String ONE_SERVER_CONNECT_STRING = "localhost:2181";

    public static void main(String[] args) throws Exception {
        try {
            CountDownLatch run = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(ONE_SERVER_CONNECT_STRING, 120000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();

            System.out.println("zk connected.");


            zooKeeper.create("/aaa", "1234".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            run.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        }

    }
}
