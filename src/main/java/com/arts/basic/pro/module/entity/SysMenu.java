package com.arts.basic.pro.module.entity;


import com.arts.basic.pro.module.entity.base.IdEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 菜单表
 * @author  lwf
 */
@Entity
@Table(name = "t_sys_menu")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysMenu extends IdEntity<SysMenu> implements Serializable {

	@Serial
	private static final long serialVersionUID = -7647070016867130608L;

	//菜单名称
	@Column(name = "title", length = 32)
	private String title;

	@Column(name = "meta_title")
	private String metaTitle;

	//路由名称
	@Column(name = "name", length = 30)
	private String name;

	//key
	@Column(name = "shiro_key", length = 30)
	private String shiroKey;

	//前台文件路径
	@Column(name = "file_location", length = 255)
	private String fileLocation;

	//联动的shirokey
	@Column(name = "cascade_key", length = 255)
	private String cascadeKey;

	//菜单链接
	@Column(name = "path", length = 255)
	private String path;

	//上级菜单id
	@Column(name = "parent_id")
	private Long parentId;

	//显示顺序
	@Column(name = "show_index")
	private Integer showIndex;

	//显示图片的class
	@Column(name = "icon", length = 50)
	private String icon;

	//按钮json串
	@Column(name = "buttons", columnDefinition = "text")
	private String buttons;

	//状态，0:不可用，1:可用
	@Column(name = "status")
	private Integer status;

	@OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Where(clause = "status = 1 ")
	@OrderBy("showIndex ASC")
	private Set<SysMenu> children = new HashSet<>();
}
