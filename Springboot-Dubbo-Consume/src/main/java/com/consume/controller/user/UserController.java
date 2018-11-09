package com.consume.controller.user;

import com.api.model.user.UserModel;
import com.consume.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wei.peng
 * @date 2018/11/7
 */

@RestController
@Api("用户模块")
public class UserController {
    @Autowired
    OrderService orderService;
    @RequestMapping(value = "/api/user",method = RequestMethod.GET)
    @ApiOperation(value = "用户模块新增", httpMethod = "GET", response = String.class, notes = "用户模块新增，提供用户管理-用户模块新增操作")
    public String insertUser(){
        UserModel model = null;
        try {
            model = new UserModel();
            model.setUserName("haha123");
            model.setAddress("怀化");
            orderService.insertUser(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model.toString();
    }
}
