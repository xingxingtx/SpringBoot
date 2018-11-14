package com.wei.config;

import com.wei.controller.user.UserController;
import com.wei.model.base.BaseModel;
import com.wei.model.order.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

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
        baseModel.setModel(getOrderModel());
        return baseModel;
    }
    @Bean
    public OrderModel getOrderModel(){
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderId("1");
        return orderModel;
    }
}
