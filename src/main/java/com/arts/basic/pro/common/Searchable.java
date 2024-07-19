package com.arts.basic.pro.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Searchable implements Serializable {

	@Serial
	private static final long serialVersionUID = 7970496669636569982L;

	private Map<String, Object> params;

	private Pageable pageable;
}
