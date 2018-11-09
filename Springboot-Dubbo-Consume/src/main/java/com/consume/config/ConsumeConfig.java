package com.consume.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2018/11/7.
 */
@Configuration
@ImportResource({ "classpath:dubbo/*.xml" })
public class ConsumeConfig {

  /*  *//**
     * 消费方应用信息，用于计算依赖关系
     *//*
    @Bean
    public ApplicationConfig getApplicationConfig(){
        ApplicationConfig config = new ApplicationConfig();
        config.setName("consume");
        config.setId("consumeId");
        return config;
    }

    *//**
     * 注册中心暴露服务地址
     *//*
    @Bean
    public RegistryConfig getRegistryConfig(){
        RegistryConfig config = new RegistryConfig();
        config.setAddress("zookeeper://localhost:2181");
        return config;
    }*/
}
