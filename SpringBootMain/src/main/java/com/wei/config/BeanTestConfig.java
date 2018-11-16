package com.wei.config;

import com.wei.model.base.BaseModel;
import com.wei.model.order.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 * @date 2018/11/13
 */
@Component
public class BeanTestConfig {
    @Bean
    public BaseModel getBaseModel(){
        BaseModel baseModel = new BaseModel();
        baseModel.setId("1");
        baseModel.setCreate("1");
        return baseModel;
    }
    @Bean
    public Product getOrderModel(){
        Product orderModel = new Product();
        return orderModel;
    }
}
