package com.provide.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2018/11/7.
 */
@Configuration
@ImportResource({ "classpath:dubbo/*.xml" })
public class ProvideConfig {

   /* *//**
     * 提供方应用信息，用于计算依赖关系
     *//*
    @Bean
    public ApplicationConfig getApplicationConfig(){
        ApplicationConfig config = new ApplicationConfig();
        config.setName("provider");
        config.setId("providerId");
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
    }

    *//**
     * 暴露服务
     *//*
    @Bean
    public ProtocolConfig getProtocolConfig(){
        ProtocolConfig config = new ProtocolConfig();
        config.setName("dubbo");
        config.setPort(20880);
        return config;
    }*/
}
