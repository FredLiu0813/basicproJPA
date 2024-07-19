package com.arts.basic.pro.module.entity.base;

import com.arts.basic.pro.utils.UuidUtil;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

/**
 * Entity 基础类
 * 
 * @author DELL
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity<T> implements Serializable {

	@Serial
	private static final long serialVersionUID = 8523698845755394690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Transient
	private String uuid;

	//创建时间
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	//更新时间
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;

	public String getUuid() {
		if (this.id != null) {
			Class<T> entityClass = getEntityClass();
			return UuidUtil.getUuid(id, entityClass);
		}
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
		//		this.getId();
	}

	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
