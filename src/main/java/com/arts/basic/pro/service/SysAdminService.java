package com.arts.basic.pro.service;

import com.arts.basic.pro.common.Searchable;
import com.arts.basic.pro.module.entity.SysAdmin;
import com.arts.basic.pro.module.repository.SysAdminDao;
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
public class SysAdminService extends EntityServiceManager<SysAdmin, Long> {

    private final SysAdminDao dao;

    @Autowired
    public SysAdminService(SysAdminDao dao) {
        this.dao = dao;
    }

    public Page<SysAdmin> getPage(Searchable searchable) {
        PageRequest pageRequest = buildPageRequest(searchable.getPageable());
        Specification<SysAdmin> spec = buildSpecification(searchable.getParams());
        return dao.findAll(spec, pageRequest);
    }

    public SysAdmin update(SysAdmin model) {
        dao.save(model);
        return model;
    }

    public SysAdmin findByAccountOrMobile(String input) {
        return dao.findByAccountOrMobile(input, input);
    }

    @Override
    protected JpaRepository<SysAdmin, Long> getEntityDao() {
        return dao;
    }
}
