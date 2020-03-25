package com.mttl.vlms.setting.stt013.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mttl.vlms.common.ApplyAspect;
import com.mttl.vlms.exception.DAOException;
import com.mttl.vlms.exception.SystemException;
import com.mttl.vlms.setting.stt013.dao.RoleDao;
import com.mttl.vlms.setting.stt013.dto.RoleDto;

/**
 * RoleServiceImpl
 * 
 * 
 *
 */
@Service("RoleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SystemException.class)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@ApplyAspect
	@Override
	public List<RoleDto> getUserList(String roleId) throws SystemException {
		List<RoleDto> result;
		try {
			result = roleDao.getUserList(roleId);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return result;
	}

	@ApplyAspect
	@Override
	public void insertRole(RoleDto roleDto, Integer createdBy) throws SystemException {
		try {
			roleDao.insertRole(roleDto, createdBy);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	@ApplyAspect
	@Override
	public int checkDuplicateRoleId(RoleDto roleDto) throws SystemException {
		int roleIdCount;
		try {
			roleIdCount = roleDao.checkDuplicateRoleId(roleDto);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), e.getMessage(), e);
		}
		return roleIdCount;
	}

}
