package com.wei.config.initpay;

import com.alipay.demo.trade.config.Configs;
import com.wei.paymodules.unionpay.util.SDKConfig;
import com.wei.paymodules.weixinpay.util.ConfigUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/**
 *
 * @author Administrator
 * @date 2018/11/12
 */
@Component
public class InitPayConfig implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //初始化 支付宝-微信-银联相关参数,涉及机密,此文件不会提交,请自行配置相关参数并加载
        /**
         * 支付宝
         */
        Configs.init("zfbinfo.properties");
        /**
         * 微信
         */
        ConfigUtil.init("wxinfo.properties");
        /**
         * 银联
         */
        SDKConfig.getConfig().loadPropertiesFromSrc();
    }
}
