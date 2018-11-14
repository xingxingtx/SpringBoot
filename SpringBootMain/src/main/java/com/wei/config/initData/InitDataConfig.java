package com.wei.config.initData;

import org.springframework.beans.factory.annotation.Value;

/**
 *配置文件参数初始化配置
 * @author Administrator
 * @date 2018/11/14
 */
public class InitDataConfig {
    /**
     * swagger是否开启配置
     */
    public static boolean swaggerShow;
    /**
     *服务地址
     */
    public static String serverContextUrl;
    /**
     * 支付宝支付通知地址
     */
    public static String alipayNotifyUrl;
    /**
     * 微信支付通知地址
     */
    public static String wexinpayNotifyUrl;
    /**
     * 银联支付通知地址
     */
    public static String unionpayNotifyUrl;

    @Value("server.context.url")
    public static void setServerContextUrl(String serverContextUrl) {
        InitDataConfig.serverContextUrl = serverContextUrl;
    }
    @Value("alipay.notify.url")
    public static void setAlipayNotifyUrl(String alipayNotifyUrl) {
        InitDataConfig.alipayNotifyUrl = alipayNotifyUrl;
    }

    @Value("wexinpay.notify.url")
    public static void setWexinpayNotifyUrl(String wexinpayNotifyUrl) {
        InitDataConfig.wexinpayNotifyUrl = wexinpayNotifyUrl;
    }
    @Value("unionpay.notify.url")
    public static void setUnionpayNotifyUrl(String unionpayNotifyUrl) {
        InitDataConfig.unionpayNotifyUrl = unionpayNotifyUrl;
    }
    @Value("${swagger.show}")
    public  void setSwaggerShow(boolean swaggerShow) {
        InitDataConfig.swaggerShow = swaggerShow;
    }
}
