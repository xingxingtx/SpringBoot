package com.wei.exceptions;

/**
 * @Author:wei.peng
 * @Desicription:业务逻辑层异常
 * @Date:Created in 2018-03-23 13:48
 * @Modified By:
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
