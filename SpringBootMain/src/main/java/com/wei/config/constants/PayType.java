package com.wei.config.constants;

/**
 *支付类型枚举
 * @author Administrator
 * @date 2018/11/12
 */

public enum PayType {
    /**支付类型*/
    ALI("支付宝",(short)1),WECHAT("微信",(short)2),UNION("银联",(short)3);

    private Short code;
    private String name;

    PayType(String name,Short code) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Short code) {
        for (PayType c : PayType.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }
}
