package com.arts.basic.pro.module.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class TokenDTO implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2161311504569425169L;

	private SysAdminTokenDTO admin;

	@ApiModelProperty("")
	private String jwtToken;
}
