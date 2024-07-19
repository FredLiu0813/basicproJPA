package com.arts.basic.pro.module.dto;

import com.arts.basic.pro.module.dto.base.BaseDTO;
import com.arts.basic.pro.utils.UuidUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysAdminDTO extends BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5519299231661756536L;

    @ApiModelProperty("用户名")
    private String account;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("账号")
    private String loginName;

    @ApiModelProperty("密码加密串")
    private String pwdKey;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("是否超级管理员")
    private Integer idSuperAdmin;

    @ApiModelProperty("公司uuid")
    private String companyUuid;

    @JsonIgnore
    private Long companyId;
    @ApiModelProperty("公司名")
    private String companyName;

    @ApiModelProperty("部门Uuid")
    private String departmentUuid;

    public String getDepartmentUuid() {
        if (departmentId != null) {
            return UuidUtil.getUuid(departmentId, "Department");
        }
        return departmentUuid;
    }

    @JsonIgnore
    private Long departmentId;

    @ApiModelProperty("部门名")
    private String department;
    
    @ApiModelProperty("团队名称")
    private String teamName;

    @ApiModelProperty("0禁用;1启用")
    private Integer status;

    @ApiModelProperty("1是专家0不是")
    private Integer isKmbpFlow;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("1是集团0不是集团是子公司")
    private Integer isGroup;

    private List<SysRoleDTO> tRoleList = new ArrayList<SysRoleDTO>();
}
