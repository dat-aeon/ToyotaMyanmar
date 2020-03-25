package com.mttl.vlms.web.setting.stt013;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.mttl.vlms.common.CommonConstants;
import com.mttl.vlms.setting.stt013.dto.RoleDto;
import com.mttl.vlms.setting.stt013.service.RoleService;
import com.mttl.vlms.user.dto.User;
import com.mttl.vlms.web.common.BaseBean;

/**
 * RoleInputBean
 * 
 * 
 */
@ManagedBean(name = "RoleInputBean")
@ViewScoped
public class RoleInputBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{RoleService}")
	private RoleService roleService;

	private RoleDto roleDto;

	private User loginUser;

	@PostConstruct
	public void init() {
		roleDto = new RoleDto();
		loginUser = (User) getSessionParam(CommonConstants.LOGIN_USER);
	}

	public String inputRole() {
		int countRoleId = roleService.checkDuplicateRoleId(roleDto);
		if (countRoleId > 0) {
			addErrorMessage(null, "MSG_CMN_010", roleDto.getRoleName());
			return null;
		}
		roleService.insertRole(roleDto, loginUser.getUserID());
		addInfoMessage(null, "MSG_CMN_002", roleDto.getRoleName());
		keepSetMessage();
		return "userInfoList";
	}

	public RoleDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
