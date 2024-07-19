package com.arts.basic.pro.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageResultMapper {

	private PageResultMapper() {

	}

	private static <E> PageResult<E> wrap(int code, String message, List<E> o, PageData pageData) {
		return new PageResult<E>(code, message, o, pageData);
	}

	public static <E> PageResult<E> wrap(List<E> o, final Page<?> pageData) {
		return wrap(RetResult.SUCCESS_CODE, RetResult.SUCCESS_MESSAGE, o, PageData.convert(pageData));
	}

	public static <E> PageResult<E> wrap(List<E> o) {
		return wrap(RetResult.SUCCESS_CODE, RetResult.SUCCESS_MESSAGE, o, null);
	}

	public static <E> PageResult<E> wrap(int code, String message) {
		return wrap(code, message, null, null);
	}

	public static <E> PageResult<E> wrap(int code) {
		return wrap(code, null, null, null);
	}

	public static <E> PageResult<E> wrap(Exception e) {
		return new PageResult<E>(RetResult.ERROR_CODE, e.getMessage(), null, null);
	}

	public static <E> PageResult<E> error(String message) {
		return wrap(RetResult.ERROR_CODE, message);
	}
	
	public static <E> PageResult<E> error(Integer code, String message) {
		return wrap(code, StringUtils.isBlank(message) ? RetResult.ERROR_MESSAGE : message);
	}

	public static <E> PageResult<E> illegalArgument() {
		return wrap(RetResult.ILLEGAL_ARGUMENT_CODE_, RetResult.ILLEGAL_ARGUMENT_MESSAGE, null, null);
	}

	/**
	 * ERROR. code=500
	 *
	 * @param <E> the type parameter
	 *
	 * @return the page result
	 */
	public static <E> PageResult<E> error() {
		return wrap(RetResult.ERROR_CODE, RetResult.ERROR_MESSAGE, null, null);
	}

	/**
	 * SUCCESS. code=200
	 *
	 * @param <E> the type parameter
	 *
	 * @return the page result
	 */
	public static <E> PageResult<E> ok() {
		return new PageResult<E>();
	}
}
