/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.basic;


/**
 * @author liubida 2011-11-8 下午8:22:55
 */
public class JavaList {
}



class MyList {

    class Node {

        private Object data;
        private Node   next;

        public Node(Object data, Node next){
            this.data = data;
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    protected Node head;
    protected int  length;

    public MyList(){
        head = new Node(null, null);
        length = 0;
    }

    public void addToHead(Object o) {
        Node node = new Node(o, null);
        node.setNext(head.getNext());
        head.setNext(node);
        length++;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    public int size() {
        return 0;
    }

}
