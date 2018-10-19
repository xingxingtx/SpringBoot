package com.wei.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by ZGP on 2018/8/2.
 * Rest接口返回数据封装bean
 */
public class JsonResponseData {

    private static Gson gson=new GsonBuilder().create();

    //空构造方法
    public JsonResponseData(){}

    //构造方法
    public JsonResponseData(Boolean ret,String msg,int status, String message, Object data){
        this.ret = ret;
        this.msg = msg;
        this.code = status;
        this.message = message;
        this.data = data==null?new Object():data;
    }


    //是否成功
    private Boolean ret = false;

    //返回信息
    private String msg = null;

    //返回状态
    private int code = -100;

    //返回状态对应消息
    private String message = null;

    //返回数据
    private Object data = null;

    public Boolean getRet() {
        return ret;
    }

    public void setRet(Boolean ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //重写toString方法
    public String toString(){
        return gson.toJson(this);
    }
}
