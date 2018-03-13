package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.server.quorum.QuorumPeerMain;

import java.util.concurrent.LinkedBlockingQueue;


/**
 * 集群
 */
public class ZookeeperDemo4 {

    public static void main(String[] args) throws Exception {

        if (args.length > 0) {
            QuorumPeerMain.main(args);
        } else {
            QuorumPeerMain.main(new String[]{"zk/zoo3.cfg"});
        }
    }
}
