package com.arts.basic.pro.module.entity;

import com.arts.basic.pro.module.entity.base.IdCreateTimeEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author
 * @date 2024/4/15
 * @apiNote
 */
@Entity
@Table(name = "t_sys_admin_log")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysAdminLog extends IdCreateTimeEntity<SysAdminLog> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4601800947466240185L;

    @Column(name = "sys_admin_id")
    private Long sysAdminId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "module_type", length = 50)
    private String moduleType;

    @Column(name = "module_type_code")
    private Integer moduleTypeCode;

    @Column(name = "view_url")
    private String viewUrl;

    @Column(name = "param", columnDefinition = "text")
    private String param;

    @Column(name = "action_type", length = 50)
    private String actionType;

    @Column(name = "action_type_code")
    private Integer actionTypeCode;

    @Column(name = "ip")
    private String ip;
}
