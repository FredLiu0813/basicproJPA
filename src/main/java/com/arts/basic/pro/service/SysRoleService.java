package com.arts.basic.pro.service;

import com.arts.basic.pro.common.Searchable;
import com.arts.basic.pro.module.entity.SysAdmin;
import com.arts.basic.pro.module.entity.SysRole;
import com.arts.basic.pro.module.repository.SysAdminDao;
import com.arts.basic.pro.module.repository.SysRoleDao;
import com.arts.basic.pro.service.base.EntityServiceManager;
import com.arts.basic.pro.utils.JwtUtil;
import com.arts.basic.pro.utils.enums.StatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class SysRoleService extends EntityServiceManager<SysRole, Long> {

	private final SysRoleDao dao;

	private final SysAdminDao sysAdminDao;

	@Autowired
	public SysRoleService(SysRoleDao dao, SysAdminDao sysAdminDao) {
		this.dao = dao;
		this.sysAdminDao = sysAdminDao;
	}

	@Override
	protected JpaRepository<SysRole, Long> getEntityDao() {
		return dao;
	}

	public Page<SysRole> getPage(Searchable searchable) {
		PageRequest pageRequest = buildPageRequest(searchable.getPageable());
		Specification<SysRole> spec = buildSpecification(searchable.getParams());
        return dao.findAll(spec, pageRequest);
	}
	
	/**
	 * 创建
	 * @param model
	 */
	public SysRole create(SysRole model) {
		Integer loginUserId = JwtUtil.getJwtInfo("id");
		SysAdmin sa = sysAdminDao.findById(loginUserId.longValue()).orElse(null);
		model.setCreateBy(sa.getId());
		model.setCreateByName(sa.getName());
		model.setStatus(StatusEnum.STATUS.getType());
		dao.save(model);
		return model;
	}

	/**
	 * 修改
	 * @param model
	 */
	public SysRole update(SysRole model) {
		Integer loginUserId = JwtUtil.getJwtInfo("id");
		SysAdmin sa = sysAdminDao.findById(loginUserId.longValue()).orElse(null);
		model.setUpdateBy(sa.getId());
		model.setUpdateByName(sa.getName());
		dao.save(model);
		return model;
	}

	public List<SysRole> findRoles(int status) {
		return dao.findByStatus(status);
	}

	public SysRole setStatus(Long id, Integer status) {
		SysRole b = dao.findById(id).orElse(null);
		if (b != null) {
			b.setStatus(status);
			dao.save(b);
		}
		return b;
	}
}