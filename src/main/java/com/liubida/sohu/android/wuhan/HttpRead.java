/*
 * Copyright 2012 sohu.com All right reserved. This software is the confidential and proprietary information of sohu.com
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with sohu.com.
 */

package com.liubida.sohu.android.wuhan;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

import org.apache.commons.lang.StringUtils;

/**
 * @author Leon, liubida Aug 23, 2012 3:59:13 PM
 */
public class HttpRead {

    private static HttpRead ins = new HttpRead();

    // public static HttpRead instance() {
    // return ins;
    // }

    public HttpRead(){
    }

    private boolean bInit = false;
    private String domain = HttpConstant.DOMAIN;// 网络连接地址
    private String accessToken = "";
    private DirectUrl2 durl = null;
    private String apiversion = HttpConstant.APIVERSION;
    private String encoding = HttpConstant.UTF_8;

    // 线程池
    private ExecutorService exec = null;
    private final int THREAD_POOL_SIZE = 1;
    private final String TAG = "HttpRead";

    // 任务池
    private final int TASK_POOL_SIZE = 50;
    // 此taskPool在Http请求时: FIFO
    private final TaskPool<HttpTask> taskPool = new TaskPool<HttpTask>(TASK_POOL_SIZE);

    public boolean init(String accessToken) {
        // 只需要开启一次, 若要改变token, 请先关闭本连接
        if (bInit) {
            return true;
        }
        // 参数校验
        if (StringUtils.isBlank(accessToken)) {
            return false;
        }
        // 创建线程池
        exec = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        try {
            this.accessToken = accessToken;
            this.durl = new DirectUrl2(accessToken);

            /**
             * 1. 线程池里有多个线程, 多个线程共享taskPool, 分别从taskPool中取task去执行
             * 2. 注意看durl里面的call_url(...)方法, 这个方法在每次调用的时候都会创建一个conn,
             * 3. taskPool自身已经做线程并发处理, 而每个conn都在线程各自的方法里面, 所以不存在线程抢占问题
             */
//            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
//                exec.execute(new AsyncTask(durl, taskPool));
//            }
            return (bInit = true);
        } catch (Exception e) {
            bInit = false;
            return false;
        }
    }

    public void cancel() {
        exec.shutdownNow();
        bInit = false;
    }

    protected String keyValueS(String key, String value) throws UnsupportedEncodingException {
        return "&" + keyValue(key, value);
    }

    protected String keyValue(String key, String value) throws UnsupportedEncodingException {
        // Translates a string into application/x-www-form-urlencoded format using a specific encoding scheme.
        String rtn = null;
        rtn = URLEncoder.encode(key, encoding) + "=" + URLEncoder.encode(value, encoding);
        return rtn;
    }

    // ====================================== Asynchronous call
    // =========================================================//
    public boolean asyncListArticle(Integer __offset, Integer __limit, FolderType __folder, OrderType __order, Handler __handler) {
        if (!bInit)
            return false;
        StringBuilder content = new StringBuilder();

        try {
            content.append(keyValue("access_token", accessToken));

            if (null != __offset) {
                content.append(keyValueS("offset", __offset.toString()));
            }
            if (null != __limit) {
                content.append(keyValueS("limit", __limit.toString()));
            }
            if (null != __folder) {
                content.append(keyValueS("folder_name", __folder.toString()));
            }
            if (null != __order) {
                content.append(keyValueS("order_by", __order.toString()));
            }

        } catch (Exception e) {
            return false;
        }

        String url = domain + apiversion + HttpConstant.ARTICLE_LIST;
        taskPool.putLast(new HttpTask(url, HttpConstant.POST, content.toString(), __handler));
        return true;
    }

    public boolean asyncGetText(String __bookmark_id, Handler __handler) {
        if (!bInit)
            return false;
        StringBuilder content = new StringBuilder();

        if (null == __bookmark_id) {
            return false;
        }

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("bookmark_id", __bookmark_id));
        } catch (Exception e) {
            return false;
        }

        String url = domain + apiversion + HttpConstant.ARTICLE_GETTEXT;
        taskPool.putLast(new HttpTask(url, HttpConstant.POST, content.toString(), __handler));
        return true;
    }

    public boolean asyncReadArticle(String __bookmark_id, Handler __handler) {
        if (!bInit)
            return false;
        StringBuilder content = new StringBuilder();

        if (null == __bookmark_id) {
            return false;
        }

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("bookmark_id", __bookmark_id));
        } catch (Exception e) {
            return false;
        }

        String url = domain + apiversion + HttpConstant.ARTICLE_READ;
        taskPool.putLast(new HttpTask(url, HttpConstant.POST, content.toString(), __handler));
        return true;
    }

    public String listArticle(Integer __offset, Integer __limit, FolderType __folder, OrderType __order) {
        if (!bInit)
            return null;
        StringBuilder content = new StringBuilder();

        try {
            content.append(keyValue("access_token", accessToken));

            if (null != __offset) {
                content.append(keyValueS("offset", __offset.toString()));
            }
            if (null != __limit) {
                content.append(keyValueS("limit", __limit.toString()));
            }
            if (null != __folder) {
                content.append(keyValueS("folder_name", __folder.toString()));
            }
            if (null != __order) {
                content.append(keyValueS("order_by", __order.toString()));
            }

        } catch (Exception e) {
            return null;
        }

        String url = domain + apiversion + HttpConstant.ARTICLE_LIST;
        return (String) durl.call_url(url, HttpConstant.POST, content.toString());
    }

    public String syncArticle(Integer __last_record, Integer __limit, Integer __include_resource) {
        if (!bInit) {
            return null;
        }
        StringBuilder content = new StringBuilder();

        try {
            content.append(keyValue("access_token", accessToken));

            if (null != __last_record) {
                content.append(keyValueS("last_sync_record", __last_record.toString()));
            }
            if (null != __limit) {
                content.append(keyValueS("limit", __limit.toString()));
            }
            if (null != __include_resource) {
                content.append(keyValueS("include_resource", __include_resource.toString()));
            }

        } catch (Exception e) {
            return null;
        }

        String url = domain + apiversion + HttpConstant.ARTICLE_SYNC;
        return (String) durl.call_url(url, HttpConstant.POST, content.toString());
    }

    public boolean asyncSyncArticle(Integer __last_record, Integer __limit, Integer __include_resource, Handler __handler) {
        if (!bInit)
            return false;
        StringBuilder content = new StringBuilder();

        try {
            content.append(keyValue("access_token", accessToken));

            if (null != __last_record) {
                content.append(keyValueS("last_sync_record", __last_record.toString()));
            }

            if (null != __limit) {
                content.append(keyValueS("limit", __limit.toString()));
            }
            if (null != __include_resource) {
                content.append(keyValueS("include_resource", __include_resource.toString()));
            }
        } catch (Exception e) {
            return false;
        }

        String url = domain + apiversion + HttpConstant.ARTICLE_SYNC;
        taskPool.putLast(new HttpTask(url, HttpConstant.POST, content.toString(), __handler));
        return true;
    }

    public boolean asyncImgGetRaw(String __key, Handler __handler) {
        if (!bInit)
            return false;
        StringBuilder content = new StringBuilder();

        if (null == __key) {
            return false;
        }

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("key", __key));
        } catch (Exception e) {
            return false;
        }

        String url = domain + apiversion + HttpConstant.IMG_GETRAW;
        taskPool.putLast(new HttpTask(url, HttpConstant.POST, content.toString(), __handler));
        return true;
    }

    public boolean asyncImgGetRaw(String __key, Integer height, Integer width, String format, Handler __handler) {
        if (!bInit) {
            return false;
        }
        StringBuilder content = new StringBuilder();

        if (null == __key) {
            return false;
        }

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("key", __key));
            if (null != height)
                content.append(keyValueS("height", height.toString()));
            if (null != width)
                content.append(keyValueS("width", width.toString()));
            if (null != format)
                content.append(keyValueS("format", format.toString()));
        } catch (Exception e) {
            return false;
        }

        String url = domain + apiversion + HttpConstant.IMG_GETRAW;
        taskPool.putLast(new HttpTask(url, HttpConstant.POST, content.toString(), __handler));
        return true;
    }

    public boolean asyncFeedback(String __content, String __email, Handler __handler) {
        if (!bInit)
            return false;
        StringBuilder content = new StringBuilder();

        if (null == __content) {
            return false;
        }

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("content", __content));

            if (null != __email)
                content.append(keyValueS("email", __email));
        } catch (Exception e) {
            return false;
        }

        String url = domain + apiversion + HttpConstant.FEED_BACK;
        taskPool.putLast(new HttpTask(url, HttpConstant.POST, content.toString(), __handler));
        return true;
    }

    public Object getImgRaw(String __key) {
        if (!bInit)
            return null;
        StringBuilder content = new StringBuilder();

        if (__key == null)
            return null;

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("key", __key));
        } catch (Exception e) {
            return null;
        }

        String url = domain + apiversion + HttpConstant.IMG_GETRAW;
        return durl.call_url(url, HttpConstant.POST, content.toString());
    }

    public Object getImgRaw(String __key, Integer height, Integer width, String format) {
        if (!bInit)
            return null;
        StringBuilder content = new StringBuilder();

        if (__key == null)
            return null;

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("key", __key));
            if (null != height)
                content.append(keyValueS("height", height.toString()));
            if (null != width)
                content.append(keyValueS("width", width.toString()));
            if (null != format)
                content.append(keyValueS("format", format.toString()));
        } catch (Exception e) {
            return null;
        }

        String url = domain + apiversion + HttpConstant.IMG_GETRAW;
        return durl.call_url(url, HttpConstant.POST, content.toString());
    }

    public Object getText(String __bookmark_id) {
        if (!bInit)
            return null;
        StringBuilder content = new StringBuilder();

        if (null == __bookmark_id)
            return null;

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("bookmark_id", __bookmark_id));
        } catch (Exception e) {
            return null;
        }

        String url = domain + apiversion + HttpConstant.ARTICLE_GETTEXT;
        return durl.call_url(url, HttpConstant.POST, content.toString());
    }

    public String readArticle(Integer bookmarkId) {
        if (!bInit) {
            return null;
        }
        StringBuilder content = new StringBuilder();

        if (bookmarkId == null) {
            return null;
        }

        try {
            content.append(keyValue("access_token", accessToken));
            content.append(keyValueS("bookmark_id", bookmarkId.toString()));
            content.append(keyValueS("include_resource", "1"));
        } catch (Exception e) {
            return null;
        }

        String url = domain + apiversion + HttpConstant.ARTICLE_READ;
        return (String) durl.call_url(url, HttpConstant.POST, content.toString());
    }

    public String getDomain() {
        return domain;// 获取连接地址
    }

    public void setApiVersion(String apiversion) {
        if (StringUtils.isBlank(apiversion)) {
            this.apiversion = HttpConstant.APIVERSION;
        } else {
            this.apiversion = apiversion;
        }
    }

    public String getApiVersion() {
        return apiversion;
    }

    //
    // public void setProxy(Proxy __proxy) {
    // durl.setProxy(__proxy);
    // }
    //
    // public void delProxy() {
    // durl.delProxy();
    // }
    //
    // public boolean isProxy()
    // {
    // return durl.isProxy();
    // }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String __encoding) {
        encoding = __encoding;
    }

    public boolean isbInit() {
        return bInit;
    }

    public void setbInit(boolean bInit) {
        this.bInit = bInit;
    }

    public void setDomain(String domain) {
        if (StringUtils.isBlank(domain)) {
            this.domain = HttpConstant.DOMAIN;
        } else {
            this.domain = null;
        }
    }
}
