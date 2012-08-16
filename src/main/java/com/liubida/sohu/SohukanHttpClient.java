/*
 * Copyright 2012 sohu.com All right reserved. This software is the confidential and proprietary information of sohu.com
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with sohu.com.
 */
package com.liubida.sohu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author liubida Aug 15, 2012 11:25:50 AM
 */

public class SohukanHttpClient {

    public void executeStreamAPI(String url, String cookie, List<NameValuePair> params) {

        BufferedReader in = null;
        HttpClient client = null;
        HttpGet request = null;
        ExecutorService exec = Executors.newFixedThreadPool(1);
        try {
            client = new DefaultHttpClient();
            request = new HttpGet();
            request.setURI(new URI(url));
            request.setHeader("Cookie", cookie);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String result = null;
            String NL = System.getProperty("line.separator");

            while (!Thread.interrupted()) {
                result = exec.submit(new worker(in)).get(40, TimeUnit.SECONDS);
                if (StringUtils.isNotBlank(result)) {
                    System.out.println(result);
                }
                else {
                    System.out.print('.');
                }
            }
        } catch (Exception e) {
            // 释放之后重连
            System.out.println("except");
        } finally {
            // request must be released before in.close()
            if (null != request) {
                request.releaseConnection();
            }
            if (null != in) {
                try {
                    in.close();
                    System.out.println("in is over");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class worker implements Callable<String> {

        private BufferedReader in = null;

        protected worker(BufferedReader in){
            this.in = in;
        }

        @Override
        public String call() throws Exception {
            String s = in.readLine();
            return s;
        }
    }

    public String executeRestAPI(String url, String cookie, List<NameValuePair> params) {

        BufferedReader in = null;
        HttpClient client = null;
        HttpPost request = null;
        try {
            client = new DefaultHttpClient();
            request = new HttpPost();
            UrlEncodedFormEntity sendentity = new UrlEncodedFormEntity(params, "utf-8");
            request.setEntity(sendentity);
            request.setURI(new URI(url));
            request.setHeader("Cookie", cookie);

            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            System.out.println(StringUtils.isBlank(sb.toString()) ? "null" : "star");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        } finally {
            if (null != request) {
                request.releaseConnection();
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        ExecutorService exec = Executors.newFixedThreadPool(2);

        exec.execute(new Runnable() {

            final String url    = "http://test.kan.sohu.com/api/2/sync/stream/";
            final String cookie = "access_token=70176199dd03aed1761328fe2d983b80fe25849d";

            @Override
            public void run() {
                SohukanHttpClient client = new SohukanHttpClient();
                client.executeStreamAPI(url, cookie, null);
                System.out.println("stream api is over");
            }
        });
        exec.execute(new Runnable() {

            private final String              url    = "http://test.kan.sohu.com/api/2/bookmarks/star/";
            private final String              cookie = "access_token=70176199dd03aed1761328fe2d983b80fe25849d";
            private final List<NameValuePair> params = Arrays.asList((NameValuePair) new BasicNameValuePair(
                                                                                                            "bookmark_id",
                                                                                                            "13"),
                                                                     (NameValuePair) new BasicNameValuePair("submit",
                                                                                                            "Submit"));

            @Override
            public void run() {
                SohukanHttpClient client = new SohukanHttpClient();
                try {
                    while (!Thread.interrupted()) {
                        client.executeRestAPI(url, cookie, params);
                        TimeUnit.SECONDS.sleep(3);
                    }
                } catch (Exception e) {
                    System.out.println("rest api is over");
                }
            }
        });
        System.out.println("123");
        System.in.read();
        System.out.println("456");
        exec.shutdownNow();
        System.out.println("main is over");
    }
}
