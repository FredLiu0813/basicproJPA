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
 * @date 2023/1/6
 * @apiNote
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoleSearchParam extends Pageable {

    @Serial
    private static final long serialVersionUID = -4859408422489160415L;

    @ApiModelProperty("角色名称")
    @SearchProperty(searchType = SearchableType.LIKE)
    private String name;

    @ApiModelProperty("1启用 0禁用 不传查全部状态")
    @SearchProperty(searchType = SearchableType.EQ)
    private Integer status;
}
