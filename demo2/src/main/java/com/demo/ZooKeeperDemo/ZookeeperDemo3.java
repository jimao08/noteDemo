package com.demo.ZooKeeperDemo;

import org.apache.zookeeper.server.quorum.QuorumPeerMain;


/**
 * 单机
 */
public class ZookeeperDemo3 {

    public static void main(String[] args) throws Exception {

        QuorumPeerMain.main(new String[]{"zk/zoo.cfg"});

    }
}
