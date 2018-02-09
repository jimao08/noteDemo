package com.demo.NioDemo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class RpcZookeeperRegister0 implements RpcRegister {

    private static final Logger logger = LoggerFactory.getLogger(RpcZookeeperRegister0.class);

    private ZooKeeper zooKeeper;

    private static volatile RpcZookeeperRegister0 register;

    private static final String MY_SERVER_PATH = "/myserver";


    private RpcZookeeperRegister0() throws Exception {
        init();
    }

    private void init() throws Exception {
        System.out.println("register zookeeper init.");

        CountDownLatch latch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper("localhost:2181", 900000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    latch.countDown();
                }
            }
        });
        latch.await();
        logger.info("zookeeper connected.");
    }

    public static RpcZookeeperRegister0 getInstance() throws Exception {

        if (register == null) {
            synchronized (RpcZookeeperRegister0.class) {
                if (register == null) {
                    register = new RpcZookeeperRegister0();
                }
            }
        }

        return register;
    }

    public synchronized void register(InetSocketAddress address, Class aClass) throws Exception {
        String serviceName = aClass.getInterfaces()[0].getSimpleName();
        String servicePath = MY_SERVER_PATH + "/" + serviceName;

        while (true) {
            try {
                Stat stat = exists(servicePath);
                if (stat == null) {
                    ArrayList<InetSocketAddress> list = new ArrayList<>();
                    list.add(address);
                    addNode(servicePath, ObjectStreamUtils.getObjectBytes(list));
                    return;
                } else {
                    byte[] data = getData(servicePath);
                    Object o = ObjectStreamUtils.readObject(data);
                    ArrayList<InetSocketAddress> addressList = (ArrayList<InetSocketAddress>) o;


                    for (InetSocketAddress a : addressList) {
                        if (a.getPort() == address.getPort() && a.getHostString().equals(address.getHostString())) {
                            logger.info("is samme server:" + a);
                            return;
                        }
                    }

                    addressList.add(address);
                    setNodeData(servicePath, ObjectStreamUtils.getObjectBytes(addressList), stat.getVersion());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public InetSocketAddress discover(String serviceName) throws Exception {
        String servicePath = MY_SERVER_PATH + "/" + serviceName;

        Stat exists = exists(servicePath);

        if (exists != null) {
            Object o = ObjectStreamUtils.readObject(getData(servicePath));
            ArrayList<InetSocketAddress> addressList = (ArrayList<InetSocketAddress>) o;

            System.out.println(addressList);

            //todo 如何做负载均衡
            return addressList.get(0);
        }

        return null;
    }

    public void unregister(InetSocketAddress address,Class aClass) throws Exception {
        //todo
        System.out.println("unregister.");
    }

    private Stat exists(String path) throws Exception {
        Stat exists = zooKeeper.exists(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });

        return exists;
    }

    @Override
    public void close() throws Exception {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
        register = null;
    }

    private void addNode(String path, byte[] data) throws Exception {
        zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    private void setNodeData(String path, byte[] data, int version) throws Exception {
        zooKeeper.setData(path, data, version);

    }

    private byte[] getData(String path) throws Exception {
        return zooKeeper.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        }, null);
    }

    private List<String> getChildren(String path) throws Exception {
        List<String> childrens = zooKeeper.getChildren(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });

        return childrens;
    }
}
