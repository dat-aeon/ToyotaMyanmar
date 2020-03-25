package com.mttl.vlms.setting.stt013.dao;

import java.util.List;

import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.setting.stt013.dto.RoleDto;

/**
 * RoleDao
 * 
 * 
 *
 */
public interface RoleDao {

	List<RoleDto> getUserList(String roleId) throws DAOException;

	void insertRole(RoleDto RoleDto, Integer createdBy) throws DAOException;

	int checkDuplicateRoleId(RoleDto RoleDto) throws DAOException;

}
