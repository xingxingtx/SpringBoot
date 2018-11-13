package com.consume.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/11/7.
 */
@Configuration
public class BeanGetConfig  implements ApplicationContextAware {
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanGetConfig.context = applicationContext;
    }

    public static Object getBean(String name) {
            return context.getBean(name);
    }

    public static Object getBean(Class<?> c) {
        return context.getBean(c);
    }

}
