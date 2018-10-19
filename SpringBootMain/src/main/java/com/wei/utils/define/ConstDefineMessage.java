package com.wei.utils.define;

/**
 * @Author:wei.peng
 * @Desicription:自定义常量信息获取
 * @Date:Created in 2018-05-24 11:05
 * @Modified By:
 */
public class ConstDefineMessage {
    //获取资讯常量信息
    public static String GetInformationMessage(int s) {
        switch (s) {
            case ConstDefine.INFORMATION_TYPE_DYNAMIC:
                return "联保动态";
                case ConstDefine.INFORMATION_TYPE_PUBLICITY:
                    return "联保公示";
            case ConstDefine.INFORMATION_TYPE_POLICY:
                return "联保政策";
                default:
                    return "";
        }
    }
    //获取通知常量信息
    public static String GetNotificationMessage(int s) {
        switch (s) {
            case ConstDefine.NOTIFICATION_TYPE_SYSTEM:
                return "系统通知";
            case ConstDefine.NOTIFICATION_TYPE_PERSON:
                return "个人通知";
            default:
                return "";
        }
    }
}
