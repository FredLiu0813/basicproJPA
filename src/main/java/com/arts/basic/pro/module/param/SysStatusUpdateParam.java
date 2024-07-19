package com.arts.basic.pro.module.param;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author
 * @date 2022/8/27
 * @apiNote
 */
@Data
public class SysStatusUpdateParam implements Serializable {

    @Serial
    private static final long serialVersionUID = -1801381776997315786L;

    private String uuid;

    private Integer status;
}
