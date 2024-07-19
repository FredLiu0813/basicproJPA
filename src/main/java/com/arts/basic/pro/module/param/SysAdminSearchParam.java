package com.arts.basic.pro.module.param;

import com.arts.basic.pro.common.Pageable;
import com.arts.basic.pro.module.annotation.SearchProperty;
import com.arts.basic.pro.utils.SearchableType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * @author
 * @date 2022/8/28
 * @apiNote
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysAdminSearchParam extends Pageable {

	@Serial
	private static final long serialVersionUID = -108819045981601752L;

	@ApiModelProperty("姓名")
	@SearchProperty(searchType = SearchableType.LIKE)
	private String name;

	@ApiModelProperty("域账号")
	@SearchProperty(searchType = SearchableType.LIKE)
	private String loginName;

	@ApiModelProperty("角色")
	@SearchProperty(searchType = SearchableType.EQ)
	private String role;

	@ApiModelProperty("角色uuid")
	private String roleUuid;

	@ApiModelProperty("状态")
	@SearchProperty(searchType = SearchableType.EQ)
	private Integer status;
}
