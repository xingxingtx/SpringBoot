package com.wei.config.properties.config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 
 * @author
 * @date 2018年10月1日 下午4:34:18
 * @version V1.0
 * @since JDK ： 1.8
 */
@Configuration
@ConfigurationProperties(prefix = "com.wei")
// PropertySource默认取application.properties
 @PropertySource(value = "myconfig.yml")
public class PropertiesConfig {

	public String type3;
	public String title3;
	public Map<String, String> login = new HashMap<String, String>();
	public List<String> urls = new ArrayList<>();

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}
	
	public String getTitle3() {
		try {
			return new String(title3.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public Map<String, String> getLogin() {
		return login;
	}

	public void setLogin(Map<String, String> login) {
		this.login = login;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
