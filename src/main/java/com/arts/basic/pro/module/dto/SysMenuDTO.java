package com.arts.basic.pro.module.dto;

import com.arts.basic.pro.module.dto.base.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表
 * @author  lwf
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysMenuDTO extends BaseDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = -1235071795091845613L;

	//菜单名称
	@ApiModelProperty("")
	private String title;

	@ApiModelProperty("")
	private String metaTitle;

	//路由名称
	@ApiModelProperty("")
	private String name;

	//key
	@ApiModelProperty("")
	private String shiroKey;

	//前台文件路径
	@ApiModelProperty("")
	private String fileLocation;

	//联动的shirokey
	@ApiModelProperty("")
	private String cascadeKey;

	//菜单链接
	@ApiModelProperty("")
	private String path;

	//上级菜单id
	@ApiModelProperty("")
	private Long parentId;

	//显示顺序
	@ApiModelProperty("")
	private Integer showIndex;

	//显示图片的class
	@ApiModelProperty("")
	private String icon;

	//按钮json串
	@ApiModelProperty("")
	private String buttons;

	//状态，0:不可用，1:可用
	@ApiModelProperty("")
	private Integer status;

	private List<SysMenuDTO> children = new ArrayList<>();
}
