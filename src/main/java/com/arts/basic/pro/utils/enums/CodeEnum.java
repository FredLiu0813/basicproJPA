package com.arts.basic.pro.utils.enums;

public enum CodeEnum {

	Success(0, "成功"), ErrorCode(100500, "数据库错误"), GO_ON_CODE(100501, "继续操作"), CONTROL(100502, "不能执行");

	private Integer code;
	private String message;

	private CodeEnum(Integer code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
