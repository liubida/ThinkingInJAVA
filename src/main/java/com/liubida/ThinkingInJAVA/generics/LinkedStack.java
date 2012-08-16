/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liubida 2012-2-26 下午3:40:11
 */
public class LinkedStack<T> {

    private class Node<U> {

        U       data;
        Node<U> next;

        Node(){
            this.data = null;
            this.next = null;
        }

        Node(U data, Node<U> next){
            this.data = data;
            this.next = next;
        }

        boolean end() {
            return this.data == null && this.next == null;
        }
    }

    private Node<T> top = new Node<T>();

    void push(T item) {
        top = new Node<T>(item, top);
    }

    T pop() {
        T ret = top.data;
        if (!top.end()) {
            top = top.next;
        }
        return ret;
    }

    public static void main(String[] args) {
        LinkedStack<String> l = new LinkedStack<String>();
        for (int i = 0; i < 8; i++) {
            l.push("i love u : " + i);
        }
        String s;
        while (null != (s = l.pop())) {
            System.out.println(s);
        }
        Map<String, Integer> m = New.map();
        m.put("1", 1);
        System.out.println(m.get("1"));
        System.out.println(new String());
        f(new HashMap<String, Integer>());
        // f(New.map());
        f(New.<String, Integer> map());
    }

    static <T> List<T> makeList(T... args) {
        List<T> ret = new ArrayList<T>();
        for (T t : args) {
            ret.add(t);
        }
        return ret;
    }

    static void f(Map<String, Integer> petPeople) {
    }
}

class New {

    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }
}
