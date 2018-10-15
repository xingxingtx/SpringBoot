package com.wei.utils.json;

import java.util.Date;

/**
 * Created by ZGP
 * JSON数据消息头
 */
public class JsonHeadData {
    //初始化构造方法
    public JsonHeadData(String token,String tenant,String IMEI){
        token = token;
        tenant = tenant;
        IMEI = IMEI;
        time = new Date().getTime();
    }

    public String token = null;

    public String tenant = null;

    public String IMEI = null;

    public long time = 0l;
}
