package com.arts.basic.pro.service.base;

import com.arts.basic.pro.common.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service层领域对象业务管理类基类.
 * 使用HibernateDao<T,PK>进行业务对象的CRUD操作,子类需重载getEntityDao()函数提供该DAO.
 * 
 * @param <T>
 *            领域对象类型
 * @param <PK>
 *            领域对象的主键类型
 * 
 *            eg.
 *            public class UserManager extends EntityManager<User, Long>{
 *            }
 * 
 * @author calvin
 */
@Transactional
public abstract class EntityServiceManager<T, PK extends Serializable> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 在子类实现此函数,为下面的CRUD操作提供DAO.
	 */
	protected abstract JpaRepository<T, PK> getEntityDao();

	// CRUD函数 //
	@Transactional(readOnly = true)
	public T get(final PK id) {
		return (T) getEntityDao().getOne(id);
	}

	@Transactional(readOnly = true)
	public T findById(final PK id) {
		Optional<T> optional = this.getEntityDao().findById(id);
		return optional.orElseThrow(() -> new RuntimeException("查找的资源不存在。"));
	}

	@Transactional(readOnly = true)
	public List<T> getAll() {
		return getEntityDao().findAll();
	}

	public void save(final T entity) {
		getEntityDao().save(entity);
	}

	public void saveAndFlush(final T entity) {
		getEntityDao().saveAndFlush(entity);
	}

	public void delete(final T entity) {
		getEntityDao().delete(entity);
	}

	public void deleteById(final PK id) {
		getEntityDao().deleteById(id);
	}

	public void saveAll(final List<T> models) {
		getEntityDao().saveAll(models);
	}

	/**
	 * 创建分页请求.
	 */
	public static PageRequest buildPageRequest(Pageable pageable) {
//		Sort sort = null;
//		if ("DESC".equals(pageable.getSortType())) {
//			sort = Sort.by(Sort.Order.desc(pageable.getSortTypeName()));
//		} else if (pageable.getSortType().equals("ASC")) {
//			sort = Sort.by(Sort.Order.asc(pageable.getSortTypeName()));
//		}
//		return PageRequest.of(pageable.getPage() - 1, pageable.getSize(), sort);

		// 改成可多列排序，逗号分隔
		Sort sort = null;
		if(StringUtils.isNotBlank(pageable.getSortType())) {
			if(StringUtils.contains(pageable.getSortType(), ",")) {
				List<Sort.Order> os = new ArrayList<>();
				String[] sts = pageable.getSortType().split(",");
				String[] stsName = pageable.getSortTypeName().split(",");
				for(int i = 0; i < sts.length; i++) {
					if(sts[i].equals("DESC")) {
						os.add(Sort.Order.desc(stsName[i]));
					}
				}
				sort = Sort.by(os);
			} else {
				if ("DESC".equals(pageable.getSortType())) {
					sort = Sort.by(Sort.Order.desc(pageable.getSortTypeName()));
				} else {
					sort = Sort.by(Sort.Order.asc(pageable.getSortTypeName()));
				}
			}
		} else {
			sort = Sort.by(Sort.Order.desc("id"));
		}
		return PageRequest.of(pageable.getPage() - 1, pageable.getSize(), sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	public static <T> Specification<T> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        return DynamicSpecifications.bySearchFilter(filters.values());
	}

}
