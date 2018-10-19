package com.wei.exceptions;

/**
 * @Author:wei.peng
 * @Desicription:数据验证异常
 * @Date:Created in 2018-05-24 14:29
 * @Modified By:
 */
public class DataValidationException extends RuntimeException{
    public DataValidationException(String messger){
        super(messger);
    }
}
