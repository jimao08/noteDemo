package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.server.quorum.QuorumPeerMain;


/**
 * 集群
 */
public class ZookeeperDemo4 {

    public static void main(String[] args) throws Exception {

        QuorumPeerMain.main(new String[]{"zk/zoo3.cfg"});

    }
}
