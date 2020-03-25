package com.mttl.vlms.setting.stt013.service;

import java.util.List;

import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt013.dto.RoleDto;

/**
 * RoleService
 * 
 * 
 *
 */
public interface RoleService {
	List<RoleDto> getUserList(String roleId) throws SystemException;

	void insertRole(RoleDto roleDto, Integer createdBy) throws SystemException;

	int checkDuplicateRoleId(RoleDto roleDto) throws SystemException;

}
