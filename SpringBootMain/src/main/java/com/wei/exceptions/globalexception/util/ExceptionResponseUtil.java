package com.wei.exceptions.globalexception.util;

/**
 * 异常响应信息工具类
 * 
 * @author wei.peng
 * @date 2018年6月8日 上午9:25:33
 * @version V1.0
 * @since JDK ： 1.7
 */
public class ExceptionResponseUtil {

	/**
	 * 
	 * init: <br/>
	 * 
	 * @author wei.peng
	 * @param code
	 * @param message
	 * @return
	 * @since JDK 1.7
	 */
	public static ExceptionResponse init(Integer code, String message) {
		return new ExceptionResponse(code, message);
	}

}
