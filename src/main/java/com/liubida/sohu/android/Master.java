/*
 * Copyright 2012 sohu.com All right reserved. This software is the
 * confidential and proprietary information of sohu.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with sohu.com.
 */
package com.liubida.sohu.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.liubida.sohu.android.config.Constant;
import com.liubida.sohu.android.wuhan.HttpRead;

/**
 * @author liubida Sep 25, 2012 5:35:17 PM
 */
public class Master {

    private static final ExecutorService exec = Executors.newCachedThreadPool();
    private static final int THREAD_NUM = 10;
    public static final String accessToken = "be5ea9377ef8ccb813b1c8cfa6cfea676aaf5676";

    public static void main(String[] args) throws InterruptedException, IOException {
        List<String> keyList = new ArrayList<String>(32);

        keyList.add("resource-image-test-4-101-6c94453ead5b37e0070b2affc1d95297c793a95c");
        keyList.add("resource-image-test-4-101-c476b7fdc22634dba2a880ca6f7cff6ddd0d3cc3");
        keyList.add("resource-image-test-4-101-6a015e0b3bd7908840f27aa104430a56ac785fdf");
        keyList.add("resource-image-test-4-101-5f7204024dc06a879a43aa1f9241577efd2f89ad");
        keyList.add("resource-image-test-4-101-afe88e5ad2d65d521e3b7ceac6241e44a13a945f");
        keyList.add("resource-image-test-4-101-60f482cd2322dbb3e4657990f31752483f1f2216");
        keyList.add("resource-image-test-4-101-51326000b05c9c2d938d26d55703c47de9890c34");
        keyList.add("resource-image-test-4-101-38c0f02a7c9db26ebed1b1a63b002ec3953e2fe6");
        keyList.add("resource-image-test-4-101-f4852d8fe99c1b7ff7b960a8d950ea9cf05719cb");
        keyList.add("resource-image-test-4-101-03af1ec6472adec85fb09168c6efe8b14156390f");
        keyList.add("resource-image-test-4-101-73e335a57b7081a304fcf30e83652c10641c49e5");
        keyList.add("resource-image-test-4-101-6e161a21754209ed1d7646f82fefeba0a3ab37ee");
        keyList.add("resource-image-test-4-101-78ae4498eb1cbc4ec4c182ebd420cb12c2499aa3");
        keyList.add("resource-image-test-4-101-40890ed96a54bc1c5a843698ca21c9ed4ebafe9a");
        keyList.add("resource-image-test-4-101-2c5f9c87fe198c47840ca0d8a8abd44eb4e8c3d3");
        keyList.add("resource-image-test-4-101-be9cda838542ca5f7902939f5ad0e54fa523e8ec");
        keyList.add("resource-image-test-4-101-8d248ed275e1814fa8bdaaa13bf6a07ba58769fc");
        keyList.add("resource-image-test-4-101-9a138a735f1d400196be13116fe272d54b794d6c");
        keyList.add("resource-image-test-4-101-5e4b2aab6d33e6c2007a538fb1096534569c6425");
        keyList.add("resource-image-test-4-101-23faf43c977bafcf7cdc78b549e6db6eba1969e0");
        keyList.add("resource-image-test-4-101-403acea3d31050989214b79ea1c916bd6557d405");
        keyList.add("resource-image-test-4-101-95db15f72932b3b79ebe844ba7d0cccaa3d60a7c");
        keyList.add("resource-image-test-4-101-3da127956a63f04a153182455732a3e4c18f38b4");
        keyList.add("resource-image-test-4-101-2c5890e905281ccc7dd9304f8d0ec2a5c70290e2");
        keyList.add("resource-image-test-4-101-2a37c295135d1afcf26accb23c2938bdfc86ae9b");
        keyList.add("resource-image-test-4-101-9bac87b5fae1bb247527ae2eef727401b667dcb6");
        keyList.add("resource-image-test-4-101-35d337523b65feb8c07faa0a34862d14f27636ba");
        keyList.add("resource-image-test-4-101-35cd774d99e92bfba31ff407609bf470123a6e23");
        keyList.add("resource-image-test-4-101-17ef3bd97198322491f430865f1847c120c173bc");
        keyList.add("resource-image-test-4-101-71d84d4c988772f91c6b4dbff3f2cbb416e39fdc");

        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch complete = new CountDownLatch(THREAD_NUM);

        for (int i = 0; i < THREAD_NUM; i++) {
            exec.execute(new Worker(accessToken, start, complete, keyList));
        }

        long begintime = 0;
        long endtime = 0;
        long costTime = 0;

        int r = System.in.read();
        if (r != -1) {
            // 预备, 跑!
            start.countDown();
            begintime = System.currentTimeMillis();
        }

        // 哥在终点等你们
        complete.await();
        endtime = System.currentTimeMillis();
        costTime = (endtime - begintime);
        System.out.println(costTime);
    }
}

class Worker implements Runnable {

    private HttpRead read = null;
    private CountDownLatch start = null;
    private CountDownLatch complete = null;
    private List<String> keyList = new ArrayList<String>();

    public Worker(String accessToken, CountDownLatch start, CountDownLatch complete, List<String> keyList){
        read = new HttpRead();
        read.init(accessToken);
        this.keyList = keyList;
        this.start = start;
        this.complete = complete;
    }

    public void run() {
        try {
            StringBuilder sb = new StringBuilder();
            start.await();
            for (String key : keyList) {
                read.getImgRaw(key, Constant.DOWNLOAD_IMAGE_HEIGHT,
                               Constant.DOWNLOAD_IMAGE_WIDTH,
                               Constant.DOWNLOAD_IMAGE_FORMAT);
                sb.append("-");
                System.out.println(sb.toString());
                // if (null != result) {
                // System.out.println(Thread.currentThread().getId() + " got the image:" + key);
                // }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            complete.countDown();// 减少一根门插销
        }
    }
}
