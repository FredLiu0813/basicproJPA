package com.arts.basic.pro.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * API接口统一返回类
 * @author DELL
 *
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class RetResult<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = -1861929314713130372L;

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
	@ApiModelProperty("返回代码(成功200，其他为失败)")
	private int code;

	/**
	 * 信息.
	 */
	@ApiModelProperty("返回信息")
	private String message;

	/**
	 * 当前时间
	 */
	@ApiModelProperty("时间戳")
	private Long now;

	/**
	 * 结果数据
	 */
	@ApiModelProperty("返回数据")
	private T data;

	public RetResult() {
		this(SUCCESS_CODE, SUCCESS_MESSAGE);
	}

	public RetResult(int code, String message) {
		this(code, message, null);
	}

	public RetResult(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.now = System.currentTimeMillis();
	}

	public RetResult(T data) {
		this();
		this.data = data;
	}

	public RetResult(Throwable e) {
		this(ERROR_CODE, e.getMessage(), null);
	}
}
