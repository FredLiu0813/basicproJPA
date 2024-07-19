package com.arts.basic.pro.module.entity;

import com.arts.basic.pro.module.entity.base.IdEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户角色
 * @author  lwf
 */
@Entity
@Table(name = "t_sys_admin_role")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysAdminRole extends IdEntity<SysAdminRole> implements Serializable {

	@Serial
	private static final long serialVersionUID = 4733567749199661324L;

	//用户id
	@Column(name = "sys_admin_id")
	private Long sysAdminId;

	//角色id
	@Column(name = "role_id")
	private Long roleId;
}
