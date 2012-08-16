/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-29
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.holding;

/**
 * @author liubida
 *
 */
import java.util.LinkedList;
import java.util.Stack;

public class MyStack<T> {
    private LinkedList<T> l = new LinkedList<T>();

    public void push(T t) {
        l.addFirst(t);
    }

    public T pop() {
        return l.removeFirst();
    }

    public T peek() {
        return l.getFirst();
    }

    public boolean empty() {
        return l.isEmpty();
    }

    public String toString() {
        return l.toString();
    }
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        
    }
}
