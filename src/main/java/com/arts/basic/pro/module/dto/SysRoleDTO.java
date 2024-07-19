package com.arts.basic.pro.module.dto;

import com.arts.basic.pro.module.dto.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 * @author  lwf
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysRoleDTO extends BaseDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 6452805923584962837L;

	//角色名称
	@ApiModelProperty("")
	@NotEmpty(message = "请输入角色名称")
	private String name;

	//描述权限
	@ApiModelProperty("")
	@NotEmpty(message = "请选择角色权限")
	private String description;

	@ApiModelProperty("创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@ApiModelProperty("修改时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	//状态，0：禁用，1：启用
	@ApiModelProperty("")
	private Integer status;

	@ApiModelProperty("创建者名称")
	private String createByName;

	@ApiModelProperty("更新者名称")
	private String updateByName;

	@ApiModelProperty("是否需要查阅范围：0不需要，1需要")
	private Integer roleRange;
}
