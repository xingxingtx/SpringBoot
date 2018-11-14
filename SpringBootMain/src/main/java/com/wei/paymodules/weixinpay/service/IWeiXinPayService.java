package com.wei.paymodules.weixinpay.service;

import com.wei.model.order.OrderModel;

/**
 *
 * @author Administrator
 * @date 2018/11/14
 */
public interface IWeiXinPayService {
    /**
     * 微信支付下单(模式二)
     * 扫码支付 还有模式一 适合固定商品ID
     * @Author  wei.peng
     * @param model
     * @return  String
     * @Date	2018/11/14
     * 更新日志
     * 2018/11/14  wei.peng 首次创建
     *
     */
    String weixinPay2(OrderModel model);
    /**
     * 微信支付下单(模式一)
     * @Author  wei.peng
     * @param model  void
     * @Date	2018/11/14
     * 更新日志
     * 2018/11/14  wei.peng 首次创建
     *
     */
    void weixinPay1(OrderModel model);
    /**
     * 微信支付退款
     * @Author  wei.peng
     * @param model
     * @return  String
     * @Date	22018/11/14
     * 更新日志
     * 2018/11/14 wei.peng 首次创建
     *
     */
    String weixinRefund(OrderModel model);
    /**
     * 关闭订单
     * @Author  wei.peng
     * @param model
     * @return  String
     * @Date	2018/11/14
     * 更新日志
     * 2018/11/14  wei.peng 首次创建
     *
     */
    String weixinCloseorder(OrderModel model);
    /**
     * 下载微信账单
     * @Author  wei.peng  void
     * @Date	2018/11/14
     * 更新日志
     * 2018/11/14 wei.peng 首次创建
     *
     */
    void saveBill();
    /**
     * 微信公众号支付返回一个url地址
     * @Author  wei.peng
     * @param model
     * @return  String
     * @Date	2018/11/14
     * 更新日志
     * 2018/11/14 wei.peng 首次创建
     *
     */
    String weixinPayMobile(OrderModel model);
    /**
     * H5支付 唤醒 微信APP 进行支付
     * 申请入口：登录商户平台-->产品中心-->我的产品-->支付产品-->H5支付
     * @Author  wei.peng
     * @param model
     * @return  String
     * @Date	2018/11/14
     * 更新日志
     *2018/11/14 wei.peng 首次创建
     *
     */
    String weixinPayH5(OrderModel model);

    /**
     * 查询订单
     * @param model
     */
    void orderquery(OrderModel model);
}
