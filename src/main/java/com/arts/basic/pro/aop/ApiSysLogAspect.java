package com.arts.basic.pro.aop;

import cn.hutool.json.JSONUtil;
import com.arts.basic.pro.common.RetResult;
import com.arts.basic.pro.module.annotation.SysLog;
import com.arts.basic.pro.module.dto.AopDTO;
import com.arts.basic.pro.module.dto.TokenDTO;
import com.arts.basic.pro.module.entity.SysAdminLog;
import com.arts.basic.pro.module.param.SysStatusUpdateParam;
import com.arts.basic.pro.service.SysAdminLogService;
import com.arts.basic.pro.utils.BeanMapper;
import com.arts.basic.pro.utils.JwtUtil;
import com.arts.basic.pro.utils.enums.StatusEnum;
import com.arts.basic.pro.utils.enums.SysActionTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author
 * @date 2022/8/18
 * @apiNote
 */
@Aspect
@Component
@Slf4j
public class ApiSysLogAspect {

	private final SysAdminLogService sysAdminLogService;

	@Autowired
	public ApiSysLogAspect(SysAdminLogService sysAdminLogService) {
		this.sysAdminLogService = sysAdminLogService;
	}

	@AfterReturning(value = "@annotation(sysLogger)", returning = "obj")
	public Object around(JoinPoint joinPoint, SysLog sysLogger, Object obj) {
		try {
			String code = JSONUtil.toJsonStr(obj);
			AopDTO aopCode = JSONUtil.toBean(code, AopDTO.class);
			if (aopCode.getCode() != 200) {
				return null;
			}

			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = null;
			if (attributes != null) {
				request = attributes.getRequest();
			}
			Object[] args = joinPoint.getArgs(); // 请求参数
			String requestParams = JSONUtil.toJsonStr(args[0]);
			SysAdminLog model = new SysAdminLog();
			model.setParam(requestParams);

			if(sysLogger.actionType().getCode() == SysActionTypeEnum.LOGIN.getCode() && !StringUtils.equals(requestParams, "\"admin\"")) {
				return null;
			}

			model.setModuleType(sysLogger.moduleType().getValue());
			if (request != null) {
				model.setIp(request.getRemoteAddr());
			}
			String name = "";
			String adName = "";
			Integer userId = null;
			if (sysLogger.flg()) {
				String jsonObj = JSONUtil.toJsonStr(obj);
				RetResult<TokenDTO> tokenDTO = JSONUtil.toBean(jsonObj, RetResult.class);
				TokenDTO dto = BeanMapper.getBeanMapper().map(tokenDTO.getData(), TokenDTO.class);
//				name = JwtUtil.getJwtInfo(dto.getJwtToken(), "name");
//				userId = JwtUtil.getJwtInfo(dto.getJwtToken(), "id");
			} else {
				name = JwtUtil.getJwtInfo("name");
				userId = JwtUtil.getJwtInfo("id");
			}
			model.setModuleTypeCode(sysLogger.moduleType().getCode());
			if (userId != null) {
				model.setSysAdminId(userId.longValue());
			}

			model.setName(name);
			if (request != null) {
				model.setViewUrl(request.getRequestURL().toString());
			}

			if(sysLogger.actionType().getCode() == SysActionTypeEnum.SETSTATUS.getCode()) {

				log.info("{}", args[0].toString());

				SysStatusUpdateParam param = (SysStatusUpdateParam)args[0];
				if(param.getStatus() == StatusEnum.STATUS.getType()) {
					model.setActionType(SysActionTypeEnum.OPEN.getValue());
					model.setActionTypeCode(SysActionTypeEnum.OPEN.getCode());
				} else {
					model.setActionType(SysActionTypeEnum.DEL.getValue());
					model.setActionTypeCode(SysActionTypeEnum.DEL.getCode());
				}
			} else {
				model.setActionType(sysLogger.actionType().getValue());
				model.setActionTypeCode(sysLogger.actionType().getCode());
			}

			sysAdminLogService.create(model);
			return null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
