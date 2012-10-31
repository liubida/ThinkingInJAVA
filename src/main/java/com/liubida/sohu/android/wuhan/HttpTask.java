package com.liubida.sohu.android.wuhan;

import java.util.logging.Handler;

public class HttpTask {

    private String target;
    private String method;
    private String content;

    public HttpTask(String __target, String __method, String __content, Handler __handler){
        target = __target;
        method = __method;
        content = __content;
    }

    public String getTarget() {
        return target;
    }

    public String getMethod() {
        return method;
    }

    public String getContent() {
        return content;
    }
}
//
// @SuppressWarnings("serial")
// public class HttpTaskPool extends LinkedList<HttpTask> {
//
// private final String TAG = "TaskPool";
// private int size = 100;
//
// public HttpTaskPool(int size){
// this.size = size;
// }
//
// synchronized void put(HttpTask t) {
// try {
// while (this.size() >= size) {
// wait();
// }
// this.add(t);
// notifyAll();
// } catch (Exception e) {
// Log.e(TAG, e.getMessage());
// }
// }
//
// synchronized HttpTask get() {
// try {
// while (this.size() <= 0) {
// wait();
// }
// HttpTask t = this.removeFirst();
// notifyAll();
// return t;
// } catch (Exception e) {
// Log.e(TAG, e.getMessage());
// return null;
// }
// }
// }
