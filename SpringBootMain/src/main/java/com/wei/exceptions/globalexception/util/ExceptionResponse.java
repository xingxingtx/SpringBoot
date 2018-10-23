package com.wei.exceptions.globalexception.util;

/**
 * 异常响应信息，最后会转换成json字符串
 * 
 * @author wei.peng
 * @date 2017年6月8日 上午9:25:48
 * @version V1.0
 * @since JDK ： 1.7
 */
public class ExceptionResponse {

	private Integer code;
	private String message;

	public ExceptionResponse(Integer code, String message) {
		this.message = message;
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
