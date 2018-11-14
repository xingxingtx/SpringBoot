package com.wei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 * Created by wei.peng on 2018/10/19.
 */
@Configuration
public abstract class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/zxc/foo").setViewName("foo");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorConfig())
                .addPathPatterns("/asd/**");
    }

  /*  *//**
     * 注册过滤器
     * @return
     *//*
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterRegistry() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new FilterConfig());
        frBean.addUrlPatterns("*//*");
        System.out.println("filter");
        return frBean;
    }

    *//**
     * 注册监听器
     * @return
     *//*
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegistry() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new
                HttpSessionListenerConfig());
        System.out.println("listener");
        return srb;
    }*/

}
