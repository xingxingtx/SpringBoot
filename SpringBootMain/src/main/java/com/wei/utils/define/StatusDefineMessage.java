package com.wei.utils.define;

/**
 * Created by tanshaoxing on 2017/11/20.
 * API常量值信息获取
 */
public class StatusDefineMessage {

    //获取常量信息
    public static String GetMessage(int s) {
        switch (s) {
            case StatusDefine.SUCCESS:
                return "操作成功";
            case StatusDefine.FAILURE:
                return "操作失败";
            case StatusDefine.U_INEXISTENCE:
                return "用户不存在";
            case StatusDefine.U_PWD_FAILED:
                return "用户密码错误";
            case StatusDefine.U_UNCHANGE_PWD:
                return "用户密码未经修改";
            case StatusDefine.U_UNACTIVE:
                return "用户未激活";
            case StatusDefine.U_TOKEN_ERROR:
                return "用户授权码错误";
            case StatusDefine.U_NO_TOKEN:
                return "用户未授权";
            case StatusDefine.U_ADD_FAILED:
                return "用户添加失败";
            case StatusDefine.U_EXIST_USER:
                return "用户已存在";
            case StatusDefine.SYS_ERROR:
                return "系统错误";
            case StatusDefine.NET_ERROR:
                return "网络请求失败";
            case StatusDefine.DB_ERROR:
                return "数据库访问失败";
            case StatusDefine.PERMISSIONDENIED:
                return "权限不足";
            case StatusDefine.U_UNLOAD:
                return "用户未登录";
            case StatusDefine.U_LOGIN_OUTTIME:
                return "登录超时,请重新登录";
            case StatusDefine.HAVEPERMISSIOND:
                return "有权限";
            case StatusDefine.SERVICE_ERROR:
                return "出现异常";
            default:
                return "";
        }
    }
}
