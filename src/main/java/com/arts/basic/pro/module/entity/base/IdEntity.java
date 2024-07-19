package com.arts.basic.pro.module.entity.base;

import java.lang.reflect.ParameterizedType;
import com.arts.basic.pro.utils.UuidUtil;
import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class IdEntity<T> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Transient
	private String uuid;

	public String getUuid() {
		if (this.id != null) {
			Class<T> entityClass = getEntityClass();
			return UuidUtil.getUuid(id, entityClass);
		}
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
