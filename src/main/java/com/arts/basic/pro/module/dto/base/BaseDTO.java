package com.arts.basic.pro.module.dto.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 *
 */
@Data
public abstract class BaseDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 7957564229069917199L;

	@ApiModelProperty("uuid")
	public String uuid;
}
