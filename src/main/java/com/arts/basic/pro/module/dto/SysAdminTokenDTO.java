package com.arts.basic.pro.module.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @date 2024/5/7
 * @apiNote
 */
@Data
@Accessors(chain = true)
public class SysAdminTokenDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6362923809091305157L;

    private String uuid;

    private String token;

    private Long createTime;

    private String account;

    private String name;

    //手机号
    private String mobile;

    private Integer idSuperAdmin;

    @ApiModelProperty("权限shiroKey")
    private List<String> rights;

    private String[] rolesUuid;
}
