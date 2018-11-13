package com;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;

/**
 * @author Administrator
 */
@EnableDubboConfig
@SpringBootApplication
public class SpringbootDubboConsumeApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringbootDubboConsumeApplication.class, args);
    }

}
