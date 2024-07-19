package com.arts.basic.pro.controller.admin;

import com.arts.basic.pro.common.RetResult;
import com.arts.basic.pro.common.RetResultMapper;
import com.arts.basic.pro.module.dto.SysMenuDTO;
import com.arts.basic.pro.module.entity.SysMenu;
import com.arts.basic.pro.service.SysMenuService;
import com.arts.basic.pro.utils.BeanMapper;
import com.arts.basic.pro.utils.enums.CodeEnum;
import com.arts.basic.pro.utils.enums.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * SysMenu的管理接口
 *
 * @author auto
 */
@RestController
@RequestMapping(value = "/sys/sysMenu")
@Api(tags = { "SysMenu接口" }, value = "SysMenu接口")
@Slf4j
public class SysMenuSysController {
	private final SysMenuService sysMenuService;

	@Autowired
	public SysMenuSysController(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	@ApiOperation(value = "系统菜单列表", notes = "")
	@GetMapping("/findMenuList")
	public RetResult<List<SysMenuDTO>> findBySystemMenus() {
		try {
			List<SysMenu> list = sysMenuService.findByStatusAndParentIdIsNullOrderByShowIndexAsc(StatusEnum.STATUS.getType());
			return RetResultMapper.ok(BeanMapper.getBeanMapper().mapList(list, SysMenuDTO.class));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return RetResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
		}
	}

	@ApiOperation(value = "系统实际菜单列表 - 权限使用", notes = "")
	@GetMapping("/findRealMenuList")
	public RetResult<List<SysMenuDTO>> findRealBySystemMenus() {
		try {
			List<SysMenu> list = sysMenuService.findByStatusAndIconIsNotNullAndParentIdIsNullOrderByShowIndexAsc(StatusEnum.STATUS.getType());
			return RetResultMapper.ok(BeanMapper.getBeanMapper().mapList(list, SysMenuDTO.class));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return RetResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
		}
	}
}
