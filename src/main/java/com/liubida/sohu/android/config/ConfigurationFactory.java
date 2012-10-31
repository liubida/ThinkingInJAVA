package com.liubida.sohu.android.config;

/**
 * 生成configuration对象的工厂类
 * 
 * @author liubida
 */
public class ConfigurationFactory {

    private static Configuration DEV = new DevConfiguration();
    @SuppressWarnings("unused")
    private static Configuration QA = new QaConfiguration();

    /**
     * 这个办法产用Configuration对象。 目前而言针对开发，测试，和产品环境，只需要修改返回的值就可以 之后可以考虑用不同的方法来适配不同的开发环境
     */
    public static Configuration getConfiguration() {
        return DEV;
    }
}
