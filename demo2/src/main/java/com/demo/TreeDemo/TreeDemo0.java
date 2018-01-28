package com.demo.TreeDemo;

/**
 * Created by myle on 2018/01/27 下午 23:33
 */

public class TreeDemo0 {

    public static void main(String[] args) {

        TreeNode<String> root = new TreeNode<>("F", null, null);

        TreeNode<String> b = new TreeNode<>("B", null, null);
        TreeNode<String> c = new TreeNode<>("C", null, null);
        TreeNode<String> d = new TreeNode<>("D", null, null);
        TreeNode<String> e = new TreeNode<>("E", null, null);
        TreeNode<String> f = new TreeNode<>("F", null, null);

//        root.addLeft(b);
//        root.addRight(c);
//
//        b.addLeft(d);
//        c.addRight(e);
//        e.addLeft(f);


        MyBinaryTree<String> tree = new MyBinaryTree<>(root);
//        tree.afterLook();


        tree.add("B");
        tree.add("E");
        tree.add("D");
        tree.add("A");
        tree.levelLook();

        System.out.println(tree.contains("D"));
        tree.mediumLook();
    }
}
