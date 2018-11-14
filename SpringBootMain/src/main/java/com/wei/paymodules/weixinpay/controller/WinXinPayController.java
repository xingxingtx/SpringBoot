package com.wei.paymodules.weixinpay.controller;

import com.wei.model.order.OrderModel;
import com.wei.paymodules.weixinpay.service.IWeiXinPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *微信支付
 * @author Administrator
 * @date 2018/11/12
 */
@RestController
@Api("微信支付")
public class WinXinPayController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

     @Autowired
    IWeiXinPayService weiXinPayService;

    @ApiOperation(value="H5支付(需要公众号内支付)")
    @RequestMapping(value="pay",method= RequestMethod.POST)
    public String  pay(@RequestBody OrderModel model, ModelMap map) {
        logger.info("H5支付(需要公众号内支付)");
        String url =  weiXinPayService.weixinPayMobile(model);
        return "redirect:"+url;
    }


}
