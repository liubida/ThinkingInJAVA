/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

import java.util.LinkedList;

/**
 * @author liubida 2011-11-26 下午2:35:30
 */
public class MyBlockingQueue<E> extends LinkedList<E> {

    private static final long serialVersionUID = 1L;
    private final static int  MAX_SIZE         = 100;
    private final Object[]    queue            = new Object[MAX_SIZE];
    private int               count;
    private int               head;
    private int               tail;

    public synchronized void put(E element) {
        while (count >= queue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        queue[tail] = element;
        tail = (tail + 1) % queue.length;
        count++;
        notifyAll();
    }

    @SuppressWarnings("unchecked")
    public synchronized E take() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        E element = (E) queue[head];
        head = (head + 1) % queue.length;
        count--;
        notifyAll();
        return element;
    }
}
