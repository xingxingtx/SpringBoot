package com.wei.exceptions;

/**
 * @Description:
 * @Author:Huangdong
 * @Date:2018/9/20 16:53
 * @Version V1.0
 */
public class DBErrorException extends RuntimeException {
    public DBErrorException(String message) {
        super(message);
    }
}
