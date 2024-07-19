package com.arts.basic.pro.service.map;

import com.arts.basic.pro.module.dto.SysAdminDTO;
import com.arts.basic.pro.module.entity.SysAdmin;
import com.arts.basic.pro.service.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author
 * @date 2024/5/7
 * @apiNote
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysAdminMapper extends BaseMapper<SysAdminDTO, SysAdmin> {
}
