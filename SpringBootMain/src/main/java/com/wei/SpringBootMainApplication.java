package com.wei;

import com.wei.model.base.BaseModel;
import com.wei.model.order.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class SpringBootMainApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMainApplication.class, args);
	}

}
