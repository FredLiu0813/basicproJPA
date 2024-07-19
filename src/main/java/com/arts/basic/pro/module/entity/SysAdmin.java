package com.arts.basic.pro.module.entity;


import com.arts.basic.pro.module.entity.base.BaseEntity;
import com.google.common.collect.ImmutableList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "t_sys_admin")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class SysAdmin extends BaseEntity<SysAdmin> implements Serializable {

	@Serial
	private static final long serialVersionUID = -316284090245712081L;

	@Column(name = "account", length = 50)
	private String account;

	@Column(name = "name", length = 20)
	private String name;

	@Column(name = "mobile", length = 12)
	private String mobile;

	@Column(name = "pwd_key", length = 20)
	private String pwdKey;

	@Column(name = "password", length = 32)
	private String password;

	@Column(name = "role", length = 50)
	private String role;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "company_name", length = 100)
	private String companyName;

	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "department", length = 20)
	private String department;

	@Column(name = "team_name", length = 50)
	private String teamName;

	@Column(name = "status")
	private int status;

	@Column(name = "create_by")
	private Long createBy;

	@Column(name = "create_by_name")
	private String createByName;

	@Column(name = "update_by")
	private Long updateBy;

	@Column(name = "update_by_name")
	private String updateByName;

	@Column(name = "is_super_admin")
	private Integer isSuperAdmin;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_sys_admin_role", joinColumns = @JoinColumn(name = "sys_admin_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	@Where(clause = "status=1")
	private List<SysRole> tRoleList;

	@Transient
	private Long[] rolesId;

	public Long[] getRolesId() {
		int i = 0;
		if (tRoleList != null && !tRoleList.isEmpty()) {
			rolesId = new Long[tRoleList.size()];
			for (SysRole tRole : tRoleList) {
				rolesId[i] = tRole.getId();
				i++;
			}
		}
		return rolesId;
	}

	@Transient
	private String roleName;

	public String getRoleName() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		if (tRoleList != null && !tRoleList.isEmpty()) {
			for (SysRole role : tRoleList) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append(role.getName());
				i++;
			}
		}
		roleName = sb.toString();
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Transient
	private List<String> roleLists;

	public void settRoleList(List<SysRole> tRoleList) {
		this.tRoleList = tRoleList;
	}

	public List<String> getRoleLists() {
		List<String> roles = new ArrayList<String>();
		if (tRoleList != null && !tRoleList.isEmpty()) {
			for (SysRole tRole : tRoleList) {
				String[] sa = StringUtils.split(tRole.getDescription(), ",");
				if (sa != null) {
					List<String> ss = ImmutableList.copyOf(sa);
					roles.addAll(ss);
				}
			}
			roleLists = roles.stream().distinct().collect(Collectors.toList());
			return roleLists;
		}
		return null;
	}
}
