package com.arts.basic.pro.module.dto.base;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidateDTO {

	/**
	 * 请求前判断
	 * @param result
	 * 		通过传过来的实体去判断
	 * @return
	 */
	public static String validate(BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> error = result.getAllErrors();
			for (ObjectError objectError : error) {
				return objectError.getDefaultMessage();
			}
		}
		return null;
	}

	/**
	 * 判断是不是 都为空
	 * @param result
	 * 		通过传过来的实体去判断
	 * @return
	 */
	public static int validateParam(BindingResult result) {
		return result.getErrorCount();
	}
}
