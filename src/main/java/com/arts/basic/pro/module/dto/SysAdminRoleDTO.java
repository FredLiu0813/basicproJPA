package com.arts.basic.pro.module.dto;

import com.arts.basic.pro.module.dto.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户角色
 * @author  lwf
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysAdminRoleDTO extends BaseDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = -1432373191892686176L;

	//用户id
	@ApiModelProperty("")
	private Long sysAdminId;

	//角色id
	@ApiModelProperty("")
	private Long roleId;
}
