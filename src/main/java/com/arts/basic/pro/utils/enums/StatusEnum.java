package com.arts.basic.pro.utils.enums;

import lombok.Data;

public enum StatusEnum {

	STATUS("启用", 1), NOSTATUS("禁用", 0);

	private String name;
	private int type;

	private StatusEnum(String name, int type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
