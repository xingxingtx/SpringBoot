package com.provide.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/11/7.
 */
@Configuration
public class ProvideConfig {

    /**
     * 提供方应用信息，用于计算依赖关系
     */
    @Bean
    public ApplicationConfig getApplicationConfig(){
        ApplicationConfig config = new ApplicationConfig();
        config.setName("provider");
        config.setId("providerId");
        return config;
    }


    /**
     * 注册中心暴露服务地址
     */
    @Bean
    public RegistryConfig getRegistryConfig(){
        RegistryConfig config = new RegistryConfig();
        config.setAddress("127.0.0.1:2181");
        config.setProtocol("zookeeper");
        return config;
    }

    /**
     * 暴露服务
     */
    @Bean
    public ProtocolConfig getProtocolConfig(){
        ProtocolConfig config = new ProtocolConfig();
        config.setName("dubbo");
        config.setPort(20880);
        return config;
    }
}
