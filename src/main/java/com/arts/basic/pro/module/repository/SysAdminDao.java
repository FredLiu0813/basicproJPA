package com.arts.basic.pro.module.repository;

import com.arts.basic.pro.module.entity.SysAdmin;
import com.arts.basic.pro.module.repository.base.BaseDao;

import java.util.List;

public interface SysAdminDao extends BaseDao<SysAdmin, Long> {

	/**
	 * 根据角色查找相应的人员
	 * @param role
	 * @return
	 */
	List<SysAdmin> findByRoleAndStatus(String role, int status);

	SysAdmin findByAccountOrMobile(String account, String mobile);
}
