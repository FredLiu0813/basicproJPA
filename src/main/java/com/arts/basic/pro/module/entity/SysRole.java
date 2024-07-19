package com.arts.basic.pro.module.entity;

import com.arts.basic.pro.module.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色表
 * @author  fred
 */
@Entity
@Table(name = "t_sys_role")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysRole extends BaseEntity<SysRole> implements Serializable {

	@Serial
	private static final long serialVersionUID = 7561679184109539926L;

	//角色名称
	@Column(name = "name", length = 50)
	private String name;

	//描述权限
	@Column(name = "description", columnDefinition = "text")
	private String description;

	//状态，0：禁用，1：启用
	@Column(name = "status")
	private Integer status;

	@Column(name = "create_by")
	private Long createBy;

	@Column(name = "create_by_name")
	private String createByName;

	@Column(name = "update_by")
	private Long updateBy;

	@Column(name = "update_by_name")
	private String updateByName;

//	@Version
//	private Long stock;
}
