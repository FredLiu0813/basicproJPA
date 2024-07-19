package com.arts.basic.pro.module.repository;

import com.arts.basic.pro.module.entity.SysAdminRole;
import com.arts.basic.pro.module.repository.base.BaseDao;

import java.util.List;

/**
 * SysUserRoleDao
 */
public interface SysAdminRoleDao extends BaseDao<SysAdminRole, Long> {

    List<SysAdminRole> findBySysAdminId(Long sysAdminId);
}
