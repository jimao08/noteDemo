package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperDemo1 {

    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        try {
            CountDownLatch run = new CountDownLatch(1);
            zooKeeper = new ZooKeeper("localhost:2181", 120000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                    System.out.println("watch ...." + event.getPath());

                    if (event.getType() == Event.EventType.NodeCreated) {
                        System.out.println("create..." + event.getPath());
                    } else if (event.getType() == Event.EventType.NodeDataChanged) {
                        System.out.println("data change:" + event.getPath());
                    } else if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
            System.out.println("zk connected.");


            Stat exists = zooKeeper.exists("/b", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeCreated) {
                        System.out.println("create2..." + event.getPath());
                    } else if (event.getType() == Event.EventType.NodeDataChanged) {
                        System.out.println("data change2:" + event.getPath());
                    }
                }
            });
            if (exists == null) {
                zooKeeper.create("/b", "1234".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            run.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        }

    }
}
