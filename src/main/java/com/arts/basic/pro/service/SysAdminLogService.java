package com.arts.basic.pro.service;

import com.arts.basic.pro.module.entity.SysAdminLog;
import com.arts.basic.pro.module.repository.SysAdminLogDao;
import com.arts.basic.pro.service.base.EntityServiceManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2024/4/18
 * @apiNote
 */
@Component
@Transactional
public class SysAdminLogService extends EntityServiceManager<SysAdminLog, Long> {

    private final SysAdminLogDao dao;

    @Autowired
    public SysAdminLogService(SysAdminLogDao dao) {
        this.dao = dao;
    }

    @Override
    protected JpaRepository<SysAdminLog, Long> getEntityDao() {
        return dao;
    }

    public SysAdminLog create(SysAdminLog model) {
        dao.save(model);
        return model;
    }
}
