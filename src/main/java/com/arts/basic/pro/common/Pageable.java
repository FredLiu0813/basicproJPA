package com.arts.basic.pro.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Pageable implements Serializable {
	@Serial
	private static final long serialVersionUID = 1766079264549201242L;

	@ApiModelProperty("页码")
	private Integer page = 1;

	@ApiModelProperty("条数")
	private Integer size = 6;

	@ApiModelProperty("升序降序")
	private String sortType = "DESC";

	@ApiModelProperty("根据什么排序")
	private String sortTypeName = "id";

}
