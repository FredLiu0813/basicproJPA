package com.arts.basic.pro.module.repository;

import com.arts.basic.pro.module.entity.SysMenu;
import com.arts.basic.pro.module.repository.base.BaseDao;

import java.util.List;

/**
 * SysMenuDao
 */
public interface SysMenuDao extends BaseDao<SysMenu, Long> {

    List<SysMenu> findByStatusAndParentIdIsNullOrderByShowIndexAsc(Integer status);

    List<SysMenu> findByStatusAndIconIsNotNullAndParentIdIsNullOrderByShowIndexAsc(Integer status);
}
