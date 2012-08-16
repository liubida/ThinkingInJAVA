/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
/**
 * @author liubida 2012-2-7 下午5:08:00
 */
public class Josephus {

    static class Node {

        int  data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    static int f(int n, int m) {
        int r = 0;
        for (int i = 2; i <= n; i++)
            r = (r + m) % i;
        return r + 1; // 这是因为日常生活中编号总是从1开始
    }

    public static void main(String[] args) {
        int n = 1000;
        int m = 72;

        Node head = new Node(0);
        Node p = head;
        for (int i = 1; i < n; i++) {
            p.next = new Node(i);
            p = p.next;
        }
        p.next = head;

        p = head;
        Node q = p;
        int i = 0;
        while (p != p.next) {
            q = p;
            for (i = 1; i < m; i++) {
                p = q;
                q = q.next;
            }
            p.next = q.next;
            q.next = null;
            p = p.next;
        }
        System.out.println(p.data + 1);
        System.out.println(f(n,m));
    }
}
