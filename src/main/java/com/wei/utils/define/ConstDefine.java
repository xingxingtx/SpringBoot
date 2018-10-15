package com.wei.utils.define;

/**
 * @Author:wei.peng
 * @Desicription:自定义常量
 * @Date:Created in 2018-05-24 11:04
 * @Modified By:
 */
public interface ConstDefine {

    //数据状态
    //禁用
    public static final int STATE_DISABLE = 2;
    //启用
    public static final int STATE_ABLE = 1;
    //删除
    public static final int STATE_DELETE=-1;

    ///消息通知-类型-系统通知
    public static final int NOTIFICATION_TYPE_SYSTEM = 1;
    //消息通知-类型-个人通知
    public static final int NOTIFICATION_TYPE_PERSON = 2;

    //资讯管理-类型-联保动态
    public static final int INFORMATION_TYPE_DYNAMIC = 1;
    //资讯管理-类型-联保公示
    public static final int INFORMATION_TYPE_PUBLICITY = 2;
    //资讯管理-类型-联保政策
    public static final int INFORMATION_TYPE_POLICY = 3;
}
