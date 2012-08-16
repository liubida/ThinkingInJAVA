/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.practise;

import java.util.Stack;

/**
 * @author liubida 2011-11-8 下午8:45:19
 */

enum Factor {
    LH("左子树高"), EH("左右等高"), RH("右子树高");

    final private String name;

    private Factor(String name){
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}

@SuppressWarnings("rawtypes")
public class AVLTree<T extends Comparable<T>> {

    public static void main(String[] args) {

        // Integer[] c = { 1, 2, 3, 4, 5, 6, 7, };
        // int[] c = { 5, 4, 6, 3, 7, 2, 1 };
        // print(BubbleSort(a));
        // print(QuickSort(b, 0, b.length - 1));
        // print(bbb(c));

        AVLTree<Integer> tree = new AVLTree<Integer>();

        // Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 50; i++) {
            tree.insert(i);
        }
        tree.inOrderByStack(tree.root);
        System.out.println();
        System.out.println(tree.getHeight(tree.root));
        // tree.inOrder(tree.root);
        // System.out.println();
        // tree1.inOrder(tree1.root);
        // System.out.println(1);
        // System.out.println(1);
        // tree.inOrderByStack(tree.root);
        // System.out.println();
        // tree.inOrderStack(tree.root);
        // System.out.println();
        // tree.preOrder(tree.root);
        // System.out.println();
        // tree.preOrderByStack(tree.root);
        // System.out.println(989);
        // System.out.println();
        // tree.afterOrder(tree.root);
        // System.out.println();
        // tree.afterOrderByStack(tree.root);
        //
        // System.out.println(a.b);
        // System.out.println("1" + 2 + 4);
    }

    static class Node<E extends Comparable<E>> {

        E       key    = null;
        Factor  factor = Factor.EH;
        Node<E> left   = null;
        Node<E> right  = null;
        Node<E> parent = null;

        Node(){
        }

        Node(E key){
            this.key = key;
        }

        public String toString() {
            String parentStr = (parent == null) ? "null" : parent.key.toString();
            String leftStr = (left == null) ? "null" : left.key.toString();
            String rightStr = (right == null) ? "null" : right.key.toString();
            return key + "[left=" + leftStr + "，right=" + rightStr + ", parent=" + parentStr + "]";
        }
    }

    private Node<T> root     = null;
    /** 当前树是否变高 */
    public boolean  isTaller = false;

    AVLTree(){
    }

    AVLTree(Node<T> p){
        root = p;
    }

    public boolean insert(T key) {
        System.out.println("insert key: " + key);
        if (new Integer(6).equals(key)) {
            System.out.println("caught it");
        }
        if (null == key) {
            return false;
        }
        if (null == root) {
            root = new Node<T>(key);
            System.out.println("insert root: " + root);
            return true;
        } else {
            return insertAVL(root, key);
        }
    }

    private boolean insertAVL(Node<T> p, T key) {
        if (p.key.compareTo(key) == 0) {
            isTaller = false;
            return false;
        }
        if (p.key.compareTo(key) > 0) {
            // 插入左子树
            if (null == p.left) {
                p.left = new Node<T>(key);
                p.left.parent = p;
                isTaller = true;
            } else {
                insertAVL(p.left, key);
            }
            if (isTaller) {
                switch (p.factor) {
                    case EH:
                        p.factor = Factor.LH;
                        isTaller = true;
                        break;
                    case LH:
                        leftBlance(p);
                        isTaller = false;
                        break;
                    case RH:
                        p.factor = Factor.EH;
                        isTaller = false;
                        break;
                }
            }
            // if (p.parent.left.key.compareTo(p.key) == 0) {
            // p.parent.left = rc;
            // } else {
            // p.parent.right = rc;
            // }
        } else {
            // 插入右子树
            if (null == p.right) {
                p.right = new Node<T>(key);
                p.right.parent = p;
                isTaller = true;
            } else {
                insertAVL(p.right, key);
            }
            if (isTaller) {
                switch (p.factor) {
                    case EH:
                        p.factor = Factor.RH;
                        isTaller = true;
                        break;
                    case LH:
                        p.factor = Factor.EH;
                        isTaller = false;
                        break;
                    case RH:
                        rightBlance(p);
                        isTaller = false;
                        break;
                }
            }
        }
        return true;
    }

    private void leftBlance(Node<T> node) {
        Node<T> lc = node.left;
        switch (lc.factor) {
            case LH:
                // 左孩子的左孩子, 单右旋
                node.factor = lc.factor = Factor.EH;
                rRotate(node);
                break;
            case RH:
                // 左孩子的右孩子, 先左旋再右旋
                Node<T> rd = lc.right;
                switch (rd.factor) {
                    case LH:
                        node.factor = Factor.RH;
                        lc.factor = Factor.EH;
                        break;
                    case EH:
                        node.factor = lc.factor = Factor.EH;
                        break;
                    case RH:
                        node.factor = Factor.EH;
                        lc.factor = Factor.LH;
                        break;
                }
                rd.factor = Factor.EH;
                lRotate(lc);
                rRotate(node);
                break;
        }
    }

    private void rightBlance(Node<T> node) {
        Node<T> rc = node.right;
        switch (rc.factor) {
            case RH:
                // 右孩子的右孩子, 单左旋
                lRotate(node);
                node.factor = rc.factor = Factor.EH;
                break;
            case LH:
                // 右孩子的左孩子, 先右旋再左旋
                Node<T> ld = rc.left;
                switch (ld.factor) {
                    case LH:
                        node.factor = Factor.EH;
                        rc.factor = Factor.RH;
                        break;
                    case EH:
                        node.factor = rc.factor = Factor.EH;
                        break;
                    case RH:
                        node.factor = Factor.LH;
                        rc.factor = Factor.EH;
                        break;
                }
                ld.factor = Factor.EH;
                rRotate(rc);
                lRotate(node);
                break;
        }
    }

    private void rRotate(Node<T> p) {
        Node<T> lc = p.left;
        Node<T> parent = p.parent;

        p.left = lc.right;
        if (null != lc.right) {
            lc.right.parent = p;
        }

        lc.right = p;
        p.parent = lc;

        lc.parent = parent;
        if (null == parent) {
            root = lc;
            return;
        } else {
            if (parent.key.compareTo(lc.key) > 0) {
                parent.left = lc;
            } else {
                parent.right = lc;
            }
        }
    }

    private void lRotate(Node<T> p) {
        Node<T> rc = p.right;
        Node<T> parent = p.parent;

        System.out.println(p);
        p.right = rc.left;
        if (null != rc.left) {
            rc.left.parent = p;
        }

        rc.left = p;
        p.parent = rc;
        rc.parent = parent;

        if (null == parent) {
            root = rc;
            return;
        }
        if (parent.key.compareTo(rc.key) > 0) {
            parent.left = rc;
        } else {
            parent.right = rc;
        }
    }

    private Stack<Node> stack = new Stack<Node>();

    void preOrderStack(Node r) {
        Node p = r;
        stack.clear();
        while (true) {
            while (null != p) {
                System.out.print(p.key + ",");
                stack.push(p);
                p = p.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            p = stack.pop();
            p = p.right;
        }
    }

    void preOrder(Node r) {
        if (null != r) {
            System.out.print(r.key + ", ");
            preOrder(r.left);
            preOrder(r.right);
        }
    }

    void inOrder(Node r) {
        if (null != r) {
            inOrder(r.left);
            System.out.print(r.key + ", ");
            inOrder(r.right);
        }
    }

    void afterOrder(Node r) {
        if (null != r) {
            afterOrder(r.left);
            afterOrder(r.right);
            System.out.print(r.key + ", ");
        }
    }

    void inOrderStack(Node r) {
        Node p = r;
        stack.clear();
        r.hashCode();
        while (true) {
            while (null != p) {
                stack.push(p);
                p = p.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            p = stack.pop();
            System.out.print(p.key + ",getHeight ");
            p = p.right;
        }
    }

    void inOrderByStack(Node r) {
        Node p = r;
        stack.clear();
        while (true) {
            while (null != p) {
                stack.push(p);
                p = p.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            p = stack.pop();
            System.out.print(p.key + ", ");
            p = p.right;
        }
    }

    void preOrderByStack(Node r) {
        Node p = r;
        stack.clear();
        while (true) {
            while (null != p) {
                System.out.print(p.key + ", ");
                stack.push(p);
                p = p.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            p = stack.pop();
            p = p.right;
        }
    }

    void afterOrderByStack(Node r) {
        Node p = r;
        Stack<Node> output = new Stack<Node>();
        stack.clear();
        stack.push(p);
        while (!stack.isEmpty()) {
            p = stack.pop();
            output.push(p);
            if (null != p.left) {
                stack.push(p.left);
            }
            if (null != p.right) {
                stack.push(p.right);
            }
        }
        while (!output.isEmpty()) {
            System.out.print(output.pop().key + ", ");
        }
    }

    int getHeight(Node r) {
        if (null == r) {
            return -1;
        }
        int left = null == r.left ? 0 : getHeight(r.left);
        int right = null == r.right ? 0 : getHeight(r.right);
        int height = left > right ? left + 1 : right + 1;
        return height;
    }

    Node<T> searchAVL(Node<T> node, T key) {
        if (null == node) {
            return null;
        }
        if (0 == node.key.compareTo(key)) {
            return node;
        }
        if (node.key.compareTo(key) > 0) {
            return searchAVL(node.left, key);
        } else {
            return searchAVL(node.right, key);
        }
    }
//
//    boolean delete(T key) {
//        if (null == key || null == root) {
//            return false;
//        }
//
//        Node<T> node = searchAVL(root, key);
//        if (null == node) {
//            return false;
//        }
//        if (root == node) {
//
//        } else {
//            if (null == node.right) {
//                if (node.parent.left.key.equals(key)) {
//                    node.parent.left = node.left;
//                } else {
//                    node.parent.right = node.left;
//                }
//                if (null == node.left) {
//
//                }
//                node.left.parent = node.parent;
//                return true;
//            }
//            if (null == node.left) {
//                if (node.parent.left.key.equals(key)) {
//                    node.parent.left = node.right;
//                } else {
//                    node.parent.right = node.right;
//                }
//                node.right.parent = node.parent;
//                return true;
//            }
//        }
//    }
}
