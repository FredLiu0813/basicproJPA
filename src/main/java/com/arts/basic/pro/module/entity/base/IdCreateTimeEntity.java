package com.arts.basic.pro.module.entity.base;

import java.lang.reflect.ParameterizedType;
import com.arts.basic.pro.utils.UuidUtil;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class IdCreateTimeEntity<T> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Transient
	private String uuid;

	//创建时间
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

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
