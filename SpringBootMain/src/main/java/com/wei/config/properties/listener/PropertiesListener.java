package com.wei.config.properties.listener;

import com.wei.config.properties.config.PropertiesListenerConfig;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;



/**
 * 配置文件监听器，用来加载自定义配置文件
 * 
 * @author
 * @date 2018年10月1日 下午3:38:25
 * @version V1.0
 * @since JDK ： 1.8
 */
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {
	
	private String propertyFileName;

	public PropertiesListener(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		PropertiesListenerConfig.loadAllProperties(propertyFileName);
	}
}
