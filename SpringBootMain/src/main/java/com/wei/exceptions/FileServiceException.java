package com.wei.exceptions;


/**
 * @Author:wei.peng
 * @Desicription: 文件服务器异常
 * @Date:Created in 2018-09-05 10:28
 * @Modified By:
 */
public class FileServiceException extends RuntimeException {
    public FileServiceException(String  message){
        super(message);
    }
}
