package com.liubida.sohu.android.wuhan;

/**
 * @author liubida Sep 25, 2012 3:58:05 PM
 */
public class AsyncTask implements Runnable {

    private final String TAG = "asyncTask";

    private boolean bStop = false;
    // private DirectUrl durl = null;
    private DirectUrl2 durl = null;
    private TaskPool<HttpTask> taskPool = null;

    public AsyncTask(DirectUrl2 durl, TaskPool<HttpTask> taskPool){
        this.durl = durl;
        this.taskPool = taskPool;
    }

    @Override
    public void run() {
        try {
            HttpTask t = null;
            Object ss = null;
            while (!Thread.interrupted() && !bStop) {
                t = taskPool.get();
                durl.error = Error.OK;
                ss = durl.call_url(t.getTarget(), t.getMethod(), t.getContent());
                if (null == ss) {
                    System.out.println("ss is null");
                }
                if (ss.getClass().equals(String.class)) {
                    System.out.println("ss:" + ss.toString().length());
                } else {
                    System.out.println("ss is done");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancel() {
        bStop = true;
    }

    public boolean isRunning() {
        return bStop;
    }
}
