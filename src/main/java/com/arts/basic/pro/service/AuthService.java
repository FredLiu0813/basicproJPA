package com.arts.basic.pro.service;

import cn.hutool.core.bean.BeanUtil;
import com.arts.basic.pro.module.dto.SysAdminDTO;
import com.arts.basic.pro.module.dto.SysAdminTokenDTO;
import com.arts.basic.pro.module.dto.TokenDTO;
import com.arts.basic.pro.module.entity.SysAdmin;
import com.arts.basic.pro.utils.BeanMapper;
import com.arts.basic.pro.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

	public TokenDTO createAdminToken(SysAdmin sysAdmin) {

		SysAdminDTO forToken = BeanMapper.getBeanMapper().map(sysAdmin, SysAdminDTO.class);
		Map<String, Object> param = BeanUtil.beanToMap(forToken);
		param.remove("id");
		String token = JwtUtil.createJWT(param);
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setJwtToken(token);

		SysAdminTokenDTO dto = new SysAdminTokenDTO();
		dto.setUuid(sysAdmin.getUuid());
		dto.setCreateTime(System.currentTimeMillis());
		dto.setName(sysAdmin.getName());
		dto.setAccount(sysAdmin.getAccount());
		dto.setMobile(sysAdmin.getMobile());
//		dto.setRolesUuid(sysAdmin.getRolesUuid());
		dto.setRights(sysAdmin.getRoleLists());
		dto.setIdSuperAdmin(sysAdmin.getIsSuperAdmin());
		tokenDTO.setAdmin(dto);

		return tokenDTO;
	}
}
