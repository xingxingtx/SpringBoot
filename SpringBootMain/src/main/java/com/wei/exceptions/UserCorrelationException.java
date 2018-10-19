package com.wei.exceptions;

/**
 * @Author:ZGP
 * @Desicription: 用户异常
 * @Date:Created in 2018/10/10 15:49
 * @Modified By:
 */
public class UserCorrelationException extends RuntimeException{

    public UserCorrelationException(String message) {
        super(message);
    }
}
