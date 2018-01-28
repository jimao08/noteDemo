package com.demo.TreeDemo;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by myle on 2018/01/27 下午 23:34
 */

public class MyBinaryTree<E> {


    private TreeNode<E> root;


    public MyBinaryTree() {
    }

    public MyBinaryTree(TreeNode<E> root) {
        this.root = root;
    }

    public void mediumLook() {
        mediumLook(root);
    }

    private void mediumLook(TreeNode<E> node) {
        if (node.left != null) {
            mediumLook(node.left);
        }
        System.out.println(node.value);
        if (node.right != null) {
            mediumLook(node.right);
        }
    }

    public void beforeLook() {
        beforeLook(root);
    }

    private void beforeLook(TreeNode<E> node) {
        System.out.println(node.value);
        if (node.left != null) {
            beforeLook(node.left);
        }
        if (node.right != null) {
            beforeLook(node.right);
        }
    }

    public void afterLook() {
        afterLook(root);
    }

    private void afterLook(TreeNode<E> node) {
        if (node.left != null) {
            afterLook(node.left);
        }
        if (node.right != null) {
            afterLook(node.right);
        }
        System.out.println(node.value);
    }

    public void levelLook() {
        Queue<TreeNode<E>> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {

            TreeNode<E> poll = queue.poll();
            System.out.println(poll.value);

            TreeNode<E> left = poll.left;

            if (left != null) {
                queue.offer(left);
            }

            TreeNode<E> right = poll.right;

            if (right != null) {
                queue.offer(right);
            }
        }

    }

    public void add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }

        if (root == null) {
            root = new TreeNode<>(e, null, null);
            return;
        }

        TreeNode<E> node = root;
        do {
            if (((Comparable) node.value).compareTo(e) >= 0) {
                if (node.left == null) {
                    node.left = new TreeNode<>(e, null, null);
                    break;
                }

                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new TreeNode<>(e, null, null);
                    break;
                }
                node = node.right;
            }
        } while (true);
    }

    public boolean contains(E e) {
        if (e == null) {
            throw new NullPointerException();
        }

        TreeNode<E> node = root;
        do {
            if (((Comparable) e).compareTo(node.value) < 0) {
                node = node.left;
            } else if (((Comparable) e).compareTo(node.value) > 0){
                node = node.right;
            } else {
                return true;
            }
        } while (node != null);

        return false;
    }
}

class TreeNode<E> {
    public E value;
    public TreeNode<E> left;
    public TreeNode<E> right;


    public TreeNode(E value, TreeNode<E> left, TreeNode<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void addLeft(TreeNode<E> left) {
        this.left = left;
    }

    public void addRight(TreeNode<E> right) {
        this.right = right;
    }
}