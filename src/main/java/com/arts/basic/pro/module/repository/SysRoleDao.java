package com.arts.basic.pro.module.repository;

import com.arts.basic.pro.module.entity.SysRole;
import com.arts.basic.pro.module.repository.base.BaseDao;

import java.util.List;

/**
 * SysRoleDao
 */
public interface SysRoleDao extends BaseDao<SysRole, Long> {

    List<SysRole> findByStatus(int status);
}
