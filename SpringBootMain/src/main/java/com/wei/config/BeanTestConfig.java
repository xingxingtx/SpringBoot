package com.wei.config;

import com.wei.model.base.BaseModel;
import com.wei.model.order.OrderModel;
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
    public OrderModel getOrderModel(){
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderId("1");
        return orderModel;
    }
}
