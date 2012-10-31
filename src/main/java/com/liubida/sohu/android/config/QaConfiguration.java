package com.liubida.sohu.android.config;


/**
 * 这个类封装了测试环境的配置信息
 * 
 * @author liubida
 */
public class QaConfiguration implements Configuration {

    private static final String push_stream_url = "http://kan.sohu.com/api/2/sync/stream";
    private static final String domain = "http://kan.sohu.com";

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getPushStreamUrl() {
        return push_stream_url;
    }

}
