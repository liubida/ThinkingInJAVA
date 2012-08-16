/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.basic;

/**
 * @author liubida 2012-2-15 下午2:42:55
 */
public class Jose {

    class Node {

        int  data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    int getJose(int n, int m) {
        Node head = new Node(1);

        Node p = head;
        for (int i = 2; i <= n; i++) {
            Node tmp = new Node(i);
            p.next = tmp;
            p = p.next;
        }
        p.next = head;

        p = head;
        Node q = head;
        while (p != p.next) {
            for (int i = 1; i < m; i++) {
                p = q;
                q = q.next;
            }
            q = q.next;
            p.next = q;
        }
        System.out.println(p.data);
        return p.data;
    }

    public static void main(String[] args) {
        new Jose().getJose(1000, 72);
    }
}
