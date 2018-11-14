package com;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
