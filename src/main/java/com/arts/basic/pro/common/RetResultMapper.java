package com.arts.basic.pro.common;

import org.apache.commons.lang3.StringUtils;

/**
 * API统一返回方法
 * @author DELL
 *
 */
public class RetResultMapper {

	/**
	 * Instantiates
	 */
	private RetResultMapper() {

	}

	/**
	 * 
	 * @param <E>
	 * @param code
	 * @param message
	 * @param o
	 * @return
	 */
	public static <E> RetResult<E> wrap(int code, String message, E o) {
		return new RetResult<>(code, message, o);
	}

	/**
	 * 
	 * @param <E>
	 * @param code
	 * @param message
	 * @return
	 */
	public static <E> RetResult<E> wrap(int code, String message) {
		return wrap(code, message, null);
	}

	public static <E> RetResult<E> wrap(int code) {
		return wrap(code, null);
	}

	public static <E> RetResult<E> wrap(Exception e) {
		return new RetResult<>(RetResult.ERROR_CODE, e.getMessage());
	}

	public static <E> E unWrap(RetResult<E> wrapper) {
		return wrapper.getData();
	}

	/**
	 *  ERROR. code=100
	 * @param <E>
	 * @return
	 */
	public static <E> RetResult<E> illegalArgument() {
		return wrap(RetResult.ILLEGAL_ARGUMENT_CODE_, RetResult.ILLEGAL_ARGUMENT_MESSAGE);
	}

	/**
	 * ERROR. code=500
	 * @param <E>
	 * @return
	 */
	public static <E> RetResult<E> error() {
		return wrap(RetResult.ERROR_CODE, RetResult.ERROR_MESSAGE);
	}

	/**
	 * ERROR. code=500 with message
	 * @param <E>
	 * @param message
	 * @return
	 */
	public static <E> RetResult<E> error(String message) {
		return wrap(RetResult.ERROR_CODE, StringUtils.isBlank(message) ? RetResult.ERROR_MESSAGE : message);
	}

	public static <E> RetResult<E> error(Integer code, String message) {
		return wrap(code, StringUtils.isBlank(message) ? RetResult.ERROR_MESSAGE : message);
	}

	/**
	 * SUCCESS. code=200
	 * @param <E>
	 * @return
	 */
	public static <E> RetResult<E> ok() {
		return new RetResult<>();
	}

	/**
	 * SUCCESS. code=200 with result
	 * @param <E>
	 * @param o
	 * @return
	 */
	public static <E> RetResult<E> ok(E o) {
		return new RetResult<>(RetResult.SUCCESS_CODE, RetResult.SUCCESS_MESSAGE, o);
	}
}
