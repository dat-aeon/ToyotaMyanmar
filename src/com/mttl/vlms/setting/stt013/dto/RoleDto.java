package com.mttl.vlms.setting.stt013.dto;

import com.mttl.vlms.common.AbstractDto;

/**
 * RoleDto
 * 
 * 
 *
 */
public class RoleDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8595701519474689172L;

	private String roleId;

	private String roleName;

	private boolean disabled;

	private String description;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}