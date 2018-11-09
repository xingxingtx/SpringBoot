package com.consume.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.api.model.user.UserModel;
import com.api.service.UserService;
import com.consume.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/11/9.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Reference(version = "1.0",url = "dubbo://192.168.1.64:20881/com.api.service.UserService")
    UserService userService;
    @Override
    public void insertUser(UserModel model) {
        userService.insertUser(model);
    }
}
