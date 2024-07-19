package com.arts.basic.pro.module.param;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author
 * @date 2024/4/18
 * @apiNote
 */
@Data
public class SysAdminLoginParam implements Serializable {

    @Serial
    private static final long serialVersionUID = -2015012376814906598L;

    @ApiModelProperty("账号或手机号")
    @NotEmpty(message = "账号或手机号不能为空")
    private String name;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
