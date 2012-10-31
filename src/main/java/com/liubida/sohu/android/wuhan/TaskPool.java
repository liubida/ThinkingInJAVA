package com.liubida.sohu.android.wuhan;

import java.util.LinkedList;

/**
 * 注意存入和取出的方式
 * 
 * @author liubida Sep 6, 2012 4:42:42 PM
 */
@SuppressWarnings("serial")
public class TaskPool<T> extends LinkedList<T> {

    private final String TAG = "TaskPool";
    private int size;

    public TaskPool(int size){
        this.size = size;
    }

    /**
     * 头部压入
     */
    public synchronized void putHead(T t) {
        try {
            while (size() >= size) {
                wait();
            }
            addFirst(t);
            notifyAll();
        } catch (Exception e) {
        }
    }

    /**
     * 头部取出
     * 
     * @return
     */
    public synchronized T get() {
        try {
            while (size() <= 0) {
                wait();
            }
            T t = removeFirst();
            notifyAll();
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 尾部压入
     */
    public synchronized void putLast(T t) {
        try {
            while (size() >= size) {
                wait();
            }
            addLast(t);
            notifyAll();
        } catch (Exception e) {
        }
    }

    // public synchronized void remove(T t) {
    // try {
    // while (size() <= 0) {
    // wait();
    // }
    // remove(t);
    // notifyAll();
    // } catch (Exception e) {
    // Log.e(TAG, e.getMessage());
    // }
    // }
}
