package com.wei.paymodules.weixinpay.service.impl;

import com.wei.model.order.OrderModel;
import com.wei.paymodules.weixinpay.service.IWeiXinPayService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 * @date 2018/11/14
 */
@Service
public class WeiXinPayServiceImpl implements IWeiXinPayService {
    @Override
    public String weixinPay2(OrderModel model) {
        return null;
    }

    @Override
    public void weixinPay1(OrderModel model) {

    }

    @Override
    public String weixinRefund(OrderModel model) {
        return null;
    }

    @Override
    public String weixinCloseorder(OrderModel model) {
        return null;
    }

    @Override
    public void saveBill() {

    }

    @Override
    public String weixinPayMobile(OrderModel model) {
        return null;
    }

    @Override
    public String weixinPayH5(OrderModel model) {
        return null;
    }

    @Override
    public void orderquery(OrderModel model) {

    }
}
