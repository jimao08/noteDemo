package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(MyServer.class);

    public static final int DEFAULT_CAPACITY = 16;

    public static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int size;

    /**
     * 哈希表扩容的阈值
     */
    private int threshold;

    /**
     * 负载因子
     */
    private double loadFactor;

    private Node<K, V>[] table;

    /**
     * 修改次数，次数不一致抛出异常
     */
    private int modCount;


    class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node next;

        public Node(int hash, K key, V value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    class TreeNode<K,V> extends Node<K,V>{
        private TreeNode<K,V> left;
        private TreeNode<K,V> right;
        private TreeNode<K,V> parent;

        public TreeNode(int hash, K key, V value, Node next) {
            super(hash, key, value, next);
        }
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, double loadFactor) {
        this.loadFactor = loadFactor;
        this.threshold = (int) (capacity * loadFactor);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        if (table == null || table.length == 0) {
            return null;
        }

        return table[hash(key)].value;
    }

    @Override
    public V put(K key, V value) {
        if (table == null || table.length == 0) {
            resize();
        }

        int index = hash(key) % table.length;
        Node<K, V> e = new Node<>(hash(key), key, value, null);

        Node<K, V> oldElement = table[index];

        if (oldElement == null) {
            table[index] = e;
        } else {
            if (oldElement.next == null) {
                oldElement.next = e;
            } else {
                Node<K, V> p = oldElement.next;
                do {
                    if (p.next == null) {
                        p.next = e;
                    }
                } while ((p = p.next) != null);
            }
        }

        modCount++;
        if (++size > threshold) {
            resize();
        }

        return null;
    }

    @Override
    public V remove(Object key) {

        modCount++;
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        size = 0;
        table = null;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }


    private static int hash(Object key) {
        return key == null ? 0 : key.hashCode();
    }


    private void resize() {
        int oldCap = table == null ? 0 : table.length;
        int oldThreshold = this.threshold;

        int newCap, newThreshold;

        if (oldCap == 0) {
            table = new Node[(int)(oldThreshold / loadFactor)];

            logger.info("new table.");
        } else {
            newCap = oldCap << 1;
            newThreshold = oldThreshold << 1;

            this.threshold = newThreshold;

            Node<K,V>[] newTable = new Node[newCap];

            for (int i = 0; i < oldCap; i++) {
                Node<K, V> p = table[i];

                if (p != null) {
                    int newIndex = p.hash % newCap;

                    if (i != newIndex) {
                        newTable[newIndex] = p;
                    } else {
                        newTable[i] = p;
                    }
                }
            }

            table = newTable;
        }
    }

    private void treeify(Node<K, V> node) {

    }
}
