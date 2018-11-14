package com;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubboConfig
@SpringBootApplication
public class SpringbootDubboProvideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDubboProvideApplication.class, args);
	}
}
