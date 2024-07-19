package com.arts.basic.pro.module.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AopDTO implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -6627238189216724665L;

	private int code;
}
