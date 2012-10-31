/**
 * 
 */
package com.liubida.sohu.android.wuhan;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.ByteBuffer;

import org.apache.commons.lang.StringUtils;

/**
 * @author Leon revision: http协议返回值在404的时候， 可能携带内容， 为适应服务器这一变化，增加response code != 200的情形，也读取返回数据。
 */
public class DirectUrl2 {

    private String accessToken = "";
    public Error error = Error.OK;
    private static HttpURLConnection conn = null;
    private static HttpURLConnection subc = null;

    public DirectUrl2(){
    }

    public DirectUrl2(String accessToken){
        this.accessToken = accessToken;
    }

    // 创建连接及需要执行的任务
    protected Object call_url(String str, String method, String content) {
        DataOutputStream dos = null;
        BufferedReader br = null;

        if (null == str) {
            error = Error.ParamNull;
            return null;
        }

        URL url = null;
        try {
            url = new URL(str);// 连接地址和命令
            // System.out.println("http to ----> " + str);
            // if (url.getProtocol().toLowerCase().equals("https")) {
            // trustAllHosts();// 检查是否为http协议
            // }
        } catch (MalformedURLException e) {
            error = Error.Url;
            return null;
        }

        if (StringUtils.isBlank(method)) {
            method = HttpConstant.GET;// 将连接方式设置为get
        }

        try {
            // if (isProxy())
            // conn = (HttpURLConnection) url.openConnection(proxy);
            // else
            {
                conn = (HttpURLConnection) url.openConnection();
                if (null != conn)
                {

                }
                // System.out.println("conn i s not null");
            }

            if (null == conn) {
                error = Error.Fail;
                return null;
            }

            {
                conn.setRequestMethod(method);// Get the request method.

                if (method.equalsIgnoreCase(HttpConstant.POST)) {
                    conn.setDoOutput(true);// 设置输出的内容
                }

                conn.setUseCaches(false);// If true, the protocol is allowed to use caching whenever it can 不使用cache
                conn.setInstanceFollowRedirects(false);
                conn.setConnectTimeout(HttpConstant.HTTP_TIMEOUT);// 设置超时时间
                conn.setRequestProperty("HTTP_X_SOHUKAN_ACCESS_TOKEN", accessToken);
                conn.setRequestProperty("Cookie", "access_token=" + accessToken);// Sets the general request property.
            }

            {
                conn.connect();
                if (null != content) {
                    dos = new DataOutputStream(conn.getOutputStream());// 设置输出
                    // System.out.println(content);
                    dos.writeBytes(content);// 输出
                    dos.flush();
                    dos.close();// 关闭输出
                    dos = null;
                }

                int code = conn.getResponseCode();// 返回的命令码
                // conn.getHeaderFields();获取头部信息，map格式其中包含content-length。
                // int length=conn.getContentLength();
                // System.out.println("length"+conn.getContentLength());
                // System.out.println("response code: " + code);
                if (302 == code) {// 如果连接失败用get方式连接
                    String redirect = conn.getHeaderField("location");
                    // int lengthall=conn.getContentLength();
                    // System.out.println(lengthall+"lengthall=");
                    // System.out.println("redirect to :" + redirect);
                    try {
                        url = new URL(redirect);
                    } catch (MalformedURLException e) {
                        error = Error.Url;
                        return null;
                    }

                    // if (isProxy())
                    // subc = (HttpURLConnection) url.openConnection(proxy);
                    // else
                    {
                        subc = (HttpURLConnection) url.openConnection();
                    }

                    if (null == subc) {
                        error = Error.Fail;
                        return null;
                    }

                    {
                        subc.setRequestMethod(HttpConstant.GET);
                        subc.setUseCaches(false);
                        subc.setInstanceFollowRedirects(false);
                        subc.setConnectTimeout(HttpConstant.HTTP_TIMEOUT);
                        // subc.setRequestProperty("HTTP_X_SOHUKAN_ACCESS_TOKEN", accessToken);
                        // subc.setRequestProperty("Cookie", "access_token=" + accessToken);
                    }

                    subc.connect();

                    int subcode = subc.getResponseCode();

                    // System.out.println("redirect code: " + subcode + " content_type: " + subc.getContentType());
                    if (200 == subcode) {

                        // System.out.println("redirect ok: " + subc.getResponseMessage());

                        int size, pos = 0;
                        // 图片最大为1M
                        byte[] bytes = new byte[1 << 20];

                        while ((size = subc.getInputStream().read(bytes, pos, 1 << 12)) > 0) {
                            pos += size;
                        }

                        ByteBuffer buffer = ByteBuffer.allocate(pos);
                        buffer.put(bytes, 0, pos);
                        // Log.d("----", "length:" + pos + "   fadsf: " + buffer.capacity());

                        error = Error.OK;
                        return buffer;
                    } else {
                        System.out.println("redirect error");
                        error = Error.Fail;
                    }
                } else if (200 == code) {
                    String tmp = null;
                    StringBuilder output = new StringBuilder();
                    InputStream in = conn.getInputStream();
                    br = new BufferedReader(new InputStreamReader(
                                                                  in));
                    while (null != (tmp = br.readLine())) {
                        output.append(tmp);
                    }
                    error = Error.OK;
                    return output;
                } else { // fixed by Leon, server may return 404, but still returns information.
                    String tmp = null;
                    StringBuilder output = new StringBuilder();
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while (null != (tmp = br.readLine())) {
                        output.append(tmp);
                    }
                    error = Error.Fail;
                    // System.out.println("outputss"+output);
                    return output;
                }
            }
        } catch (SocketTimeoutException ste) {
            error = Error.Timeout;
            return null;
        } catch (IOException e) {
            error = Error.NetIO;
            return null;
        } catch (Exception e) {
            error = Error.Fail;
            return null;
        } finally {
            if (null != br)
                try {
                    br.close();
                } catch (Exception ee) {
                }

            if (null != dos)
                try {
                    dos.flush();
                    dos.close();
                } catch (Exception ee) {
                }

            // if (null != conn) {
            // conn.disconnect();
            // }
            // if (null != subc) {
            // subc.disconnect();
            // }

            dos = null;
            conn = null;
        }

        error = Error.Fail;
        return null;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;//
    }
}
