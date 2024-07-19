package com.arts.basic.pro.controller.admin;

import com.arts.basic.pro.common.*;
import com.arts.basic.pro.module.annotation.SysLog;
import com.arts.basic.pro.module.dto.SysAdminDTO;
import com.arts.basic.pro.module.dto.TokenDTO;
import com.arts.basic.pro.module.dto.base.ValidateDTO;
import com.arts.basic.pro.module.entity.SysAdmin;
import com.arts.basic.pro.module.param.SysAdminLoginParam;
import com.arts.basic.pro.module.param.SysAdminSearchParam;
import com.arts.basic.pro.service.AuthService;
import com.arts.basic.pro.service.SysAdminService;
import com.arts.basic.pro.utils.BeanMapper;
import com.arts.basic.pro.utils.HashPwd;
import com.arts.basic.pro.utils.SearchableUtil;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @date 2024/4/16
 * @apiNote
 */
@RestController
@RequestMapping(value = "/admin/sysAdminSys")
@Api(tags = { "SysAdmin接口" }, value = "SysAdmin接口")
@Slf4j
public class SysAdminSysController {

    private final SysAdminService sysAdminService;

    private final AuthService authService;

    @Autowired
    public SysAdminSysController(SysAdminService sysAdminService, AuthService authService) {
        this.sysAdminService = sysAdminService;
        this.authService = authService;
    }

    @ApiOperation(httpMethod = "POST", value = "列表", notes = "")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public PageResult<SysAdminDTO> page(@RequestBody SysAdminSearchParam param) {
        try {
            Searchable searchable = SearchableUtil.getSearchableParam(param);
            searchable.setPageable(param);
            Page<SysAdmin> page = sysAdminService.getPage(searchable);

            List<SysAdminDTO> adminSysDTOs = BeanMapper.getBeanMapper().mapList(page.getContent(), SysAdminDTO.class);
            for (SysAdminDTO adminSysDTO : adminSysDTOs) {
                if (StringUtils.isNotBlank(adminSysDTO.getTeamName())) {
                    adminSysDTO.setDepartment(adminSysDTO.getDepartment() + "-" + adminSysDTO.getTeamName());
                }
            }
            return PageResultMapper.wrap(adminSysDTOs, page);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return PageResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
        }
    }

    @ApiOperation(httpMethod = "POST", value = "用户登录", notes = "")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @SysLog(moduleType = SysModuleTypeEnum.LOGIN, actionType = SysActionTypeEnum.LOGIN, flg = true)
    public RetResult<TokenDTO> login(@RequestBody @Valid SysAdminLoginParam param, BindingResult result) {
        try {
            String messageResult = ValidateDTO.validate(result);
            if (messageResult != null) {
                return RetResultMapper.error(messageResult);
            }

            SysAdmin sysAdmin = sysAdminService.findByAccountOrMobile(param.getName());
            if (sysAdmin == null) {
                return RetResultMapper.error("用户不存在");
            }
            if (sysAdmin.getStatus() == StatusEnum.NOSTATUS.getType()) {
                return RetResultMapper.error("用户已被禁用");
            }
            String enPass = HashPwd.encryptPassword(param.getPassword(), sysAdmin.getPwdKey());
            if (!StringUtils.equals(enPass, sysAdmin.getPassword())) {
                return RetResultMapper.error("用户名或密码错误");
            }

            TokenDTO token = authService.createAdminToken(sysAdmin);
            return RetResultMapper.ok(token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return RetResultMapper.error(CodeEnum.ErrorCode.getCode(), e.getMessage());
        }
    }
}
