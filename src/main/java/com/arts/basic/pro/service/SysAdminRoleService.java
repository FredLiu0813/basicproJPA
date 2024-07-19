package com.arts.basic.pro.service;

import com.arts.basic.pro.common.Searchable;
import com.arts.basic.pro.module.entity.SysAdminRole;
import com.arts.basic.pro.module.repository.SysAdminRoleDao;
import com.arts.basic.pro.service.base.EntityServiceManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2024/4/16
 * @apiNote
 */
@Component
@Transactional
public class SysAdminRoleService extends EntityServiceManager<SysAdminRole, Long> {

    private final SysAdminRoleDao dao;

    @Autowired
    public SysAdminRoleService(SysAdminRoleDao dao) {
        this.dao = dao;
    }

    @Override
    protected JpaRepository<SysAdminRole, Long> getEntityDao() {
        return dao;
    }

    public Page<SysAdminRole> getPage(Searchable searchable) {
        PageRequest pageRequest = buildPageRequest(searchable.getPageable());
        Specification<SysAdminRole> spec = buildSpecification(searchable.getParams());
        return dao.findAll(spec, pageRequest);
    }

    /**
     * 创建
     * @param model
     */
    public SysAdminRole create(SysAdminRole model) {
        dao.save(model);
        return model;
    }

    /**
     * 修改
     * @param model
     */
    public SysAdminRole update(SysAdminRole model) {
        dao.save(model);
        return model;
    }
}
