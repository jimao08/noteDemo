package com.demo.ListDemo;

import java.util.*;

/**
 * Created by myle on 2018/01/27 下午 17:43
 */

public class MyLinkedList<E> implements List<E>, Queue<E> {


    private int size;
    private Node<E> head;
    private Node<E> tail;

    private class Node<E> {
        E value;
        Node prev;
        Node next;

        public Node(E value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
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
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    @Override
    public boolean add(E e) {

        if (head == null && tail == null) {
            Node n = new Node(e, null, null);
            head = tail = n;
        } else if (tail != null) {
            Node n = new Node(e, tail, null);
            tail.next = n;
            tail = n;
        }

        size++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        add(e);
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }

    @Override
    public E poll() {
        return isEmpty() ? null : remove(0);
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return head.value;
    }

    @Override
    public E peek() {
        return head != null ? head.value : null;
    }

    @Override
    public boolean remove(Object o) {

        Node<E> node = head;
        for (int i = 0; i < size; i++) {
            if (o == null && node.value == null) {
                removeNode(node);
                return true;
            } else if (o != null && o.equals(node.value)) {
                removeNode(node);
                return true;
            }

            node = node.next;
        }
        return false;
    }

    @Override
    public void clear() {
        Node<E> n = head;
        for (int i = 0; i < size; i++) {
            n.value = null;
            n = n.next;
        }

        head = tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return find(index).value;
    }

    @Override
    public String toString() {

        Node<E> node = head;
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        int i = 0;
        try {
            for (; i < size; i++) {
                sb.append(node.value);

                if (node != tail) {
                    sb.append(',');
                }

                node = node.next;
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("error, i=" + i);
        }
        sb.append("]");

        return sb.toString();
    }

    private Node<E> find(int index) {
        Node<E> n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n;
    }

    @Override
    public E set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = find(index);
        E oldValue = node.value;
        node.value = element;

        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            add(element);
        } else if (index == 0) {
            Node<E> newNode = new Node<>(element, null, head);
            head = newNode;
            size++;
        } else {
            Node<E> n = find(index);
            Node<E> prevNode = n.prev;

            Node<E> newNode = new Node<>(element, prevNode, n);
            n.prev = newNode;
            prevNode.next = newNode;

            size++;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = find(index);
        removeNode(node);
        return node.value;
    }

    private void removeNode(Node<E> node) {
        if (node == head) {
            head = node.next;
        } else if (node == tail) {
            Node<E> prevNode = node.prev;
            prevNode.next = null;
            tail = prevNode;
        } else {
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        size--;
    }

    @Override
    public int indexOf(Object o) {

        Node<E> n = head;
        for (int i = 0; i < size; i++) {
            if (o == null && n.value == null) {
                return i;
            } else if (o != null && n.value.equals(o)) {
                return i;
            }

            n = n.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

}
