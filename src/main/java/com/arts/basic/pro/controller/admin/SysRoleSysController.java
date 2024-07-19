package com.arts.basic.pro.controller.admin;

import com.arts.basic.pro.common.*;
import com.arts.basic.pro.module.annotation.SysLog;
import com.arts.basic.pro.module.dto.SysRoleDTO;
import com.arts.basic.pro.module.dto.base.ValidateDTO;
import com.arts.basic.pro.module.entity.SysRole;
import com.arts.basic.pro.module.param.SysRoleSearchParam;
import com.arts.basic.pro.module.param.SysStatusUpdateParam;
import com.arts.basic.pro.service.SysRoleService;
import com.arts.basic.pro.utils.BeanMapper;
import com.arts.basic.pro.utils.SearchableUtil;
import com.arts.basic.pro.utils.UuidUtil;
import com.arts.basic.pro.utils.enums.CodeEnum;
import com.arts.basic.pro.utils.enums.StatusEnum;
import com.arts.basic.pro.utils.enums.SysActionTypeEnum;
import com.arts.basic.pro.utils.enums.SysModuleTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * SysRole的管理接口
 *
 * @author auto
 */
@RestController
@RequestMapping(value = "/sys/sysRole")
@Api(tags = { "SysRole接口" }, value = "SysRole接口")
@Slf4j
public class SysRoleSysController {

	private final SysRoleService sysRoleService;

	@Autowired
	public SysRoleSysController(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	/**
	 *
	 * @param param
	 * @return
	 */
	@ApiOperation(httpMethod = "POST", value = "列表", notes = "")
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public PageResult<SysRoleDTO> page(@RequestBody SysRoleSearchParam param) {
		try {
			param.setSortType("ASC");
			Searchable searchable = SearchableUtil.getSearchableParam(param);
			searchable.setPageable(param);
			Page<SysRole> page = sysRoleService.getPage(searchable);
			return PageResultMapper.wrap(BeanMapper.getBeanMapper().mapList(page.getContent(), SysRoleDTO.class), page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return PageResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
		}
	}

	@ApiOperation(value = "查询可用角色列表", notes = "")
	@GetMapping("/findRoleList")
	public RetResult<List<SysRoleDTO>> findBySystemMenus() {
		try {
			List<SysRole> list = sysRoleService.findRoles(StatusEnum.STATUS.getType());
			return RetResultMapper.ok(BeanMapper.getBeanMapper().mapList(list, SysRoleDTO.class));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return RetResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
		}
	}

	/**
	 * 
	 * @param dto
	 * @param result
	 * @return
	 */
	@ApiOperation(httpMethod = "POST", value = "创建", notes = "")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@SysLog(moduleType = SysModuleTypeEnum.ROLE, actionType = SysActionTypeEnum.CREATE)
	public RetResult<?> create(@RequestBody @Valid SysRoleDTO dto, BindingResult result) {
		try {
			String messageResult = ValidateDTO.validate(result);
			if (messageResult != null) {
				return RetResultMapper.error(messageResult);
			}
			SysRole model = BeanMapper.getBeanMapper().map(dto, SysRole.class);
			sysRoleService.create(model);
			return RetResultMapper.ok();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return RetResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
		}
	}

	/**
	 * 更新操作
	 *
	 * @return 更新后资源
	 */
	@ApiOperation(httpMethod = "POST", value = "更新操作")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@SysLog(moduleType = SysModuleTypeEnum.ROLE, actionType = SysActionTypeEnum.UPDATE)
	public RetResult<?> update(@RequestBody @Valid SysRoleDTO dto, BindingResult result) {
		try {
			String messageResult = ValidateDTO.validate(result);
			if (messageResult != null) {
				return RetResultMapper.error(messageResult);
			}
			Long id = UuidUtil.getId(dto.getUuid(), SysRole.class);
			SysRole model = sysRoleService.findById(id);
			BeanMapper.getBeanMapper().copyToModel(dto, model, true);
			sysRoleService.update(model);
			return RetResultMapper.ok();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return RetResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
		}
	}

	@ApiOperation(httpMethod = "POST", value = "", notes = "")
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@SysLog(moduleType = SysModuleTypeEnum.ROLE, actionType = SysActionTypeEnum.SETSTATUS)
	public RetResult<?> updateStatus(@RequestBody SysStatusUpdateParam param) {
		try {
			if (param == null) {
				return RetResultMapper.error("参数不能为空");
			}
			if (StringUtils.isBlank(param.getUuid())) {
				return RetResultMapper.error("uuid不能为空");
			}
			if (param.getStatus() == null) {
				return RetResultMapper.error("状态不能为空");
			}

			Long id = UuidUtil.getId(param.getUuid(), SysRole.class);
			sysRoleService.setStatus(id, param.getStatus());
			return RetResultMapper.ok();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return RetResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
		}
	}
}
