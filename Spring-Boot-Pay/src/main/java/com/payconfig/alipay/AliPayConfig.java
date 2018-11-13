package com.payconfig.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;

/**
 *支付宝支付公共配置
 * @author Administrator
 * @date 2018/11/12
 */
public class AliPayConfig {
    /**
     * 私有的默认构造子，保证外界无法直接实例化
     */
    private AliPayConfig(){}

    /**
     * 签名方式
     */
    public static String SIGN_TYPE = "RSA2";
    /**
     * 参数类型
     */
    public static String PARAM_TYPE = "json";
    /**
     * 编码
     */
    public static String CHARSET = "utf-8";

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private  static AlipayClient alipayClient = new DefaultAlipayClient(
                Configs.getOpenApiDomain(), Configs.getAppid(),
                Configs.getPrivateKey(), PARAM_TYPE, CHARSET,
                Configs.getAlipayPublicKey(),"RSA2");

        private  static AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }

    /**
     * 支付宝APP请求客户端实例
     * @Author  wei.peng
     * @return  AlipayClient
     * @Date	2018年11月12日
     *
     *
     */
    public static AlipayClient getAlipayClient(){
        return SingletonHolder.alipayClient;
    }
    /**
     * 电脑端预下单
     * @Author  wei.peng
     * @return  AlipayClient
     * @Date	2018年11月12日
     *
     */
    public static AlipayTradeService getAlipayTradeService(){
        return SingletonHolder.tradeService;
    }
}
