package com.arts.basic.pro.service;

import com.arts.basic.pro.common.Searchable;
import com.arts.basic.pro.module.entity.SysMenu;
import com.arts.basic.pro.module.repository.SysMenuDao;
import com.arts.basic.pro.service.base.EntityServiceManager;
import com.arts.basic.pro.utils.UuidUtil;
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
public class SysMenuService extends EntityServiceManager<SysMenu, Long> {

	private final SysMenuDao dao;

	@Autowired
	public SysMenuService(SysMenuDao dao) {
		this.dao = dao;
	}

	@Override
	protected JpaRepository<SysMenu, Long> getEntityDao() {
		return dao;
	}

	public Page<SysMenu> getPage(Searchable searchable) {
		PageRequest pageRequest = buildPageRequest(searchable.getPageable());
		Specification<SysMenu> spec = buildSpecification(searchable.getParams());
        return dao.findAll(spec, pageRequest);
	}
	
	/**
	 * 创建
	 * @param model
	 */
	public SysMenu create(SysMenu model) {
		dao.save(model);
		return model;
	}

	/**
	 * 修改
	 * @param model
	 */
	public SysMenu update(SysMenu model) {
		dao.save(model);
		return model;
	}

	
	/**
	 * 禁用或者开启
	 * @param uuid
	 * @return
	 */
	public SysMenu switchModel(String uuid) {
		Long id = UuidUtil.getId(uuid, SysMenu.class);
		SysMenu model = dao.findById(id).orElseThrow(() -> new RuntimeException("查找的资源不存在。"));
		int status = model.getStatus() == 0 ? 1 : 0;
		model.setStatus(status);
		dao.save(model);
		return model;
	}

	public List<SysMenu> findByStatusAndParentIdIsNullOrderByShowIndexAsc(Integer status) {
		return dao.findByStatusAndParentIdIsNullOrderByShowIndexAsc(status);
	}

	public List<SysMenu> findByStatusAndIconIsNotNullAndParentIdIsNullOrderByShowIndexAsc(Integer status) {
		return dao.findByStatusAndIconIsNotNullAndParentIdIsNullOrderByShowIndexAsc(status);
	}
}