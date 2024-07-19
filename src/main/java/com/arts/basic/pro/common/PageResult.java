package com.arts.basic.pro.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<T> {

	/**
	 * 成功码.
	 */
	public static final int SUCCESS_CODE = 200;

	/**
	 * 成功信息.
	 */
	public static final String SUCCESS_MESSAGE = "操作成功";

	/**
	 * 错误码.
	 */
	public static final int ERROR_CODE = 500;

	/**
	 * 错误信息.
	 */
	public static final String ERROR_MESSAGE = "内部异常";

	/**
	 * 错误码：参数非法
	 */
	public static final int ILLEGAL_ARGUMENT_CODE_ = 100;

	/**
	 * 错误信息：参数非法
	 */
	public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";

	/**
	 * 编号.
	 */
	private int code;

	/**
	 * 信息.
	 */
	private String message;

	/**
	 * 当前时间
	 */
	private Long now;

	/**
	 * list 集合
	 */
	private List<T> data;

	private PageData pageData;

	/**
	 * Instantiates a new Page result.
	 */
	PageResult() {
		super();
	}

	/**
	 * Instantiates a new Page result.
	 *
	 * @param code    the code
	 * @param message the message
	 */
	public PageResult(int code, String message) {
		this(code, message, null, null);
	}

	public PageResult(List<T> data, PageData pageData) {
		this(SUCCESS_CODE, SUCCESS_MESSAGE, data, pageData);
	}

	public PageResult(List<T> data) {
		this(SUCCESS_CODE, SUCCESS_MESSAGE, data, null);
	}

	PageResult(int code, String message, List<T> data, PageData pageData) {
		this.setCode(code);
		this.setMessage(message);
		this.setData(data);
		this.setPageData(pageData);
		this.setNow(System.currentTimeMillis());
	}
	//	
	//	PUBLIC PAGERESULT<T> PAGEUTIL(PAGEDATA PAGEDATA) {
	//		THIS.SETPAGEDATA(PAGEDATA);
	//		RETURN THIS;
	//	}
}
