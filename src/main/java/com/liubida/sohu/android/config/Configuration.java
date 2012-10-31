package com.liubida.sohu.android.config;

/**
 * 某一些配置项对于开发环境，测试环境，产品环境会采用不同的值，这个接口抽象出配置项的信息，对于不同的环境有不同的实现方式 对于跟环境无关的项，请定义在系统常量中
 * 
 * @author liubida
 */
public interface Configuration {

    String getPushStreamUrl();

    String getDomain();
}
