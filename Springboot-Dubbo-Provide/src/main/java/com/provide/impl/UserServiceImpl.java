package com.provide.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.api.model.user.UserModel;
import com.api.service.UserService;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/11/7.
 */
@Service(version = "1.0")
@Component
public class UserServiceImpl implements UserService {
    @Override
    public void insertUser(UserModel model) {

    }
}
